package com.dilip.posSystem.service.impl;

import com.dilip.posSystem.domain.StoreStatus;
import com.dilip.posSystem.exceptions.UserException;
import com.dilip.posSystem.mapper.StoreMapper;
import com.dilip.posSystem.modal.Store;
import com.dilip.posSystem.modal.StoreContact;
import com.dilip.posSystem.modal.User;
import com.dilip.posSystem.payload.dto.StoreDto;
import com.dilip.posSystem.repository.StoreRepository;
import com.dilip.posSystem.service.StoreService;
import com.dilip.posSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final UserService userService;

    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {
        Store store = StoreMapper.toEntity(storeDto,user);
        return StoreMapper.toDTO((storeRepository.save(store)));
    }

    @Override
    public StoreDto getStoreById(Long id) throws Exception {
        Store store = storeRepository.findById(id).orElseThrow(
                ()->  new Exception("Store Not found")
        );
        return StoreMapper.toDTO(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
        List<Store> dtos= storeRepository.findAll();
       return dtos.stream().map(StoreMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() throws Exception {
        User admin = userService.getCurrentUser();
        return storeRepository.findByAdminId(admin.getId());
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws Exception {

        User currentUser = userService.getCurrentUser() ;
        Store existing =  storeRepository.findByAdminId(currentUser.getId());
        if(existing==null){
            throw new Exception("Store not found");
        }
        existing.setBrand(storeDto.getBrand());
        existing.setDescription(storeDto.getDescription());
        if(storeDto.getStoreType()!=null){
            existing.setStoreType(storeDto.getStoreType());
        }
        if(storeDto.getContact()!=null){
            StoreContact contact = StoreContact.builder()
                    .address(storeDto.getContact().getAddress())
                    .phone(storeDto.getContact().getPhone())
                    .email(storeDto.getContact().getEmail())
                    .build();
            existing.setContact(contact);
        }


        Store updatedStore = storeRepository.save(existing);


        return StoreMapper.toDTO(updatedStore);
    }

    @Override
    public void deleteStore(Long id) throws Exception {

        Store store = getStoreByAdmin();
        storeRepository.delete(store);

    }

    @Override
    public StoreDto getStoreByEmployee() throws UserException {
        User currentUser =  userService.getCurrentUser();
        if(currentUser == null){
            throw new UserException("You dont have permission to access the store.");
        }
        return StoreMapper.toDTO(currentUser.getStore());
    }

    @Override
    public StoreDto moderateStore(Long id, StoreStatus status) throws Exception {
        Store store = storeRepository.findById(id).orElseThrow(
                ()-> new Exception("Store not found...")
        );
        store.setStatus(status);

        Store updatedStore = storeRepository.save(store);
        return StoreMapper.toDTO(store);
    }
}
