package com.dilip.posSystem.service.impl;

import com.dilip.posSystem.domain.PaymentType;
import com.dilip.posSystem.exceptions.UserException;
import com.dilip.posSystem.mapper.ShiftReportMapper;
import com.dilip.posSystem.modal.*;
import com.dilip.posSystem.payload.dto.ShiftReportDto;
import com.dilip.posSystem.repository.OrderRepository;
import com.dilip.posSystem.repository.RefundRepository;
import com.dilip.posSystem.repository.ShiftReportRepository;
import com.dilip.posSystem.repository.UserRepository;
import com.dilip.posSystem.service.ShiftReportService;
import com.dilip.posSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftReportServiceImpl implements ShiftReportService {

    private final ShiftReportRepository shiftReportRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final RefundRepository refundRepository;
    private final OrderRepository orderRepository;

    @Override
    public ShiftReportDto startShift() throws Exception {
        User currentUser =  userService.getCurrentUser();
        LocalDateTime shiftStart = LocalDateTime.now();
        LocalDateTime startOfDay = shiftStart.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = shiftStart.withHour(23).withMinute(59).withSecond(59);
        Optional<ShiftReport> existing = shiftReportRepository.findByCashierAndShiftStartBetween(
                currentUser,startOfDay,endOfDay
        );
        if(existing.isPresent()){
            throw new Exception("Shift already started today");
        }


        ShiftReport shiftReport = ShiftReport.builder()
                .cashier(currentUser)
                .shiftStart(shiftStart)
                .branch(currentUser.getBranch())
                .build();
        ShiftReport savedReport = shiftReportRepository.save(shiftReport);

        return ShiftReportMapper.toDTO(savedReport);
    }

    @Override
    public ShiftReportDto endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception {
        User currentUser = userService.getCurrentUser();
        ShiftReport shiftReport = shiftReportRepository
                .findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(currentUser)
                .orElseThrow(()-> new Exception("Shift not found"));
        shiftReport.setShiftEnd(shiftEnd);

        List<Refund> refunds = refundRepository.findByCashierIdAndCreatedAtBetween(
                currentUser.getId(), shiftReport.getShiftStart(),shiftReport.getShiftEnd()
        );
        double totalRefunds = refunds.stream()
                .mapToDouble(refund -> refund.getAmount()!=null?
                        refund.getAmount():0.0).sum();
        List<Order> orders = orderRepository.findByCashierAndCreatedAtBetween(
                currentUser,shiftReport.getShiftStart(),shiftReport.getShiftEnd()
        );
        double totalSales = orders.stream()
                .mapToDouble(Order::getTotalAmount).sum();
        int totalOrders = orders.size();

        double netSales = totalSales - totalRefunds;

        shiftReport.setTotalRefunds(totalRefunds);
        shiftReport.setTotalOrders(totalOrders);
        shiftReport.setTotalSales(totalSales);
        shiftReport.setNetSales(netSales);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSummaries(getPaymentSummaries(orders,totalSales));
        shiftReport.setRefunds(refunds);

        ShiftReport savedReport = shiftReportRepository.save(shiftReport);
        return ShiftReportMapper.toDTO(savedReport);
    }

    @Override
    public ShiftReportDto getShiftReportById(Long id) throws Exception {
        return ShiftReportMapper.toDTO(shiftReportRepository.findById(id).orElseThrow(()-> new Exception("Shift report not found")));
    }

    @Override
    public List<ShiftReportDto> getAllShiftReports() {
        return shiftReportRepository.findAll().stream().map(ShiftReportMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ShiftReportDto> getShiftReportByBranchId(Long branchId) {
        return shiftReportRepository.findByBranchId(branchId).stream().map(ShiftReportMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ShiftReportDto> getShiftReportByCashierId(Long cashierId) {
        return shiftReportRepository.findByCashierId(cashierId).stream().map(ShiftReportMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ShiftReportDto getCurrentShiftProgress(Long cashierId) throws Exception {
        User currentUser = userService.getCurrentUser();

        ShiftReport shift = shiftReportRepository
                .findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(currentUser)
                .orElseThrow(()-> new Exception("No active Shift report found"));

        LocalDateTime now  = LocalDateTime.now();

        List<Order> orders = orderRepository.findByCashierAndCreatedAtBetween(
                currentUser, shift.getShiftStart(), now
        );

        List<Refund> refunds = refundRepository.findByCashierIdAndCreatedAtBetween(
                currentUser.getId(),
                shift.getShiftStart(), shift.getShiftEnd()
        );
        double totalRefunds = refunds.stream()
                .mapToDouble(
                        refund -> refund.getAmount()!=null?
                                refund.getAmount():0.0
                ).sum();

        double totalSales = orders.stream()
                .mapToDouble(Order::getTotalAmount).sum();

        int totalOrders = orders.size();

        double netSales = totalSales - totalRefunds;

        shift.setTotalRefunds(totalRefunds);
        shift.setTotalOrders(totalOrders);
        shift.setTotalSales(totalSales);
        shift.setNetSales(netSales);
        shift.setRecentOrders(getRecentOrders(orders));
        shift.setTopSellingProducts(getTopSellingProducts(orders));
        shift.setPaymentSummaries(getPaymentSummaries(orders,totalSales));
        shift.setRefunds(refunds);

        ShiftReport savedReport = shiftReportRepository.save(shift);

        return ShiftReportMapper.toDTO(savedReport);
    }

    @Override
    public ShiftReportDto getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception {
        User cashier = userRepository.findById(cashierId).orElseThrow(
                ()-> new Exception("Cashier  not found with given ID : "+ cashierId)
        );
        LocalDateTime start = date.withHour(0).withMinute(0).withSecond(0).withMinute(0);
        LocalDateTime end = date.withHour(23).withMinute(59).withSecond(59).withMinute(0);

        return ShiftReportMapper.toDTO(
                shiftReportRepository
                        .findByCashierAndShiftStartBetween(cashier,start,end)
                        .orElseThrow(
                                ()-> new Exception("Shift report not found for cashier")
                        )
        );
    }


    // ----------- Helper Methods---------------

    private List<PaymentSummary> getPaymentSummaries(List<Order> orders, double totalSales) {
         Map<PaymentType,List<Order>> grouped = orders.stream()
                 .collect(Collectors.groupingBy(order->order.getPaymentType()!=null?order.getPaymentType():PaymentType.CASH));

         List<PaymentSummary> summaries = new ArrayList<>();
         for(Map.Entry<PaymentType,List<Order>> entry : grouped.entrySet()){
             double amount = entry.getValue().stream()
                     .mapToDouble(Order::getTotalAmount).sum();
             int transaction = entry.getValue().size();
             double percent = (amount/totalSales) * 100;
             PaymentSummary ps = new PaymentSummary();
             ps.setType(entry.getKey());
             ps.setTotalAmount(amount);
             ps.setTransactionCount(transaction);
             ps.setPercentage(percent);
             summaries.add(ps);
         }
         return summaries;
    }

    private List<Product> getTopSellingProducts(List<Order> orders) {
     Map<Product,Integer> productSalesMap = new HashMap<>();
     for(Order order : orders){
         for(OrderItem item :order.getItems()){
             Product product = item.getProduct();
             productSalesMap.put(product,productSalesMap.getOrDefault(product,0)+item.getQuantity());
         }
     }
     return productSalesMap.entrySet().stream()
             .sorted((a,b)->b.getValue().compareTo(a.getValue()))
             .limit(5)
             .map(Map.Entry::getKey)
             .collect(Collectors.toList());
    }

    private List<Order> getRecentOrders(List<Order> orders) {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getCreatedAt)
                .reversed())
                .limit(5)
                .collect(Collectors.toList());

    }

}
