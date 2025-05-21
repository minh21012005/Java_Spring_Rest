package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.domain.dto.Meta;
import vn.hoidanit.jobhunter.domain.dto.ResultPaginationDTO;
import vn.hoidanit.jobhunter.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
    }

    public void deleteUserByID(long id) {
        this.userRepository.deleteById(id);
    }

    public User fetchUserById(long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    public ResultPaginationDTO fetchAllUser(Pageable pageable) {
        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        Page<User> page = this.userRepository.findAll(pageable);
        Meta meta = new Meta();
        meta.setPage(page.getNumber() + 1);
        meta.setPageSize(page.getSize());
        meta.setPages(page.getTotalPages());
        meta.setTotal(page.getTotalElements());
        resultPaginationDTO.setMeta(meta);
        resultPaginationDTO.setResult(page.getContent());
        return resultPaginationDTO;
    }

    public User updateUser(User user) {
        Optional<User> currentUser = this.userRepository.findById(user.getId());
        if (currentUser.isPresent()) {
            currentUser.get().setEmail(user.getEmail());
            currentUser.get().setName(user.getName());
            currentUser.get().setPassword(user.getPassword());
            return this.userRepository.save(currentUser.get());
        }
        return null;
    }

    public User fetchUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
