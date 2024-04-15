package com.dzungyb.btn_web.service;

import com.dzungyb.btn_web.entity.User;
import com.dzungyb.btn_web.entity.UserOut;
import com.dzungyb.btn_web.error.ErrorException;
import com.dzungyb.btn_web.model.UserModel;
import com.dzungyb.btn_web.model.dto.UserDto;
import com.dzungyb.btn_web.model.dto.UserDtoOut;
import com.dzungyb.btn_web.repository.UserRepository;
import com.dzungyb.btn_web.utils.Valids;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRep;

    private User ToUserEntity(UserDto userDto) {
        return User.create(
                0,
                userDto.getName(),
                userDto.getGender(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getRoles()
        );
    }

    private UserDtoOut ToUserDtoOut(User userEntity) {
        return UserDtoOut.create(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getGender(),
                userEntity.getEmail()
        );
    }

    public UserOut login(String username, String password) {
        User user = userRep.findFirstByUsername(username);
        if (user != null && user.getPassword().contentEquals(password)) {
            return UserOut.create(user.getId(), user.getName(), user.getGender(), user.getUsername(), user.getEmail());
        }
        return null;
    }

    public List<UserModel> getAllUser() {
        List<UserModel> listUser = new ArrayList<>();
        List<User> listUserEntity = userRep.findAll();
        for (User user : listUserEntity) {
            listUser.add(UserModel.toUsersModel(user));
        }
        return listUser;
    }

    public List<UserDtoOut> getAllStudent() {
        List<User> listUserEntity = userRep.findAllByRoles("student");
        List<UserDtoOut> studentList = new ArrayList<>();
        for (User user : listUserEntity) {
            studentList.add(ToUserDtoOut(user));
        }
        return studentList;
    }

    public UserModel searchById(int id) throws ErrorException {
        User userEntity = userRep.findFirstById(id);
        if (userEntity == null) throw new ErrorException("User not found!");
        return UserModel.toUsersModel(userEntity);
    }

    public UserModel searchByEmail(String email) throws ErrorException {
        User userEntity = userRep.findFirstByEmail(email);
        if (userEntity == null) throw new ErrorException("User not found!");
        return UserModel.toUsersModel(userEntity);
    }

    public void register(UserDto userDto) throws ErrorException {
        if (Valids.isEmpty(userDto.getName())) throw new ErrorException("Name is empty");
        if (Valids.isEmpty(userDto.getGender()))
            throw new ErrorException("Gender is empty");
        if (Valids.isEmpty(userDto.getEmail())) throw new ErrorException("Email is empty");
        if (Valids.isEmpty(userDto.getPassword()))
            throw new ErrorException("Password is empty");
        if (userRep.existsByEmail(userDto.getEmail()))
            throw new ErrorException("Email is already exist");
        User userEntity = ToUserEntity(userDto);
        userRep.save(userEntity);
    }

    public void registerAdmin(UserDto userDto) throws ErrorException {
        if (Valids.isEmpty(userDto.getName())) throw new ErrorException("Name is empty");
        if (Valids.isEmpty(userDto.getGender()))
            throw new ErrorException("Gender is empty");
        if (Valids.isEmpty(userDto.getEmail())) throw new ErrorException("Email is empty");
        if (Valids.isEmpty(userDto.getPassword()))
            throw new ErrorException("Password is empty");
        if (userRep.existsByEmail(userDto.getEmail()))
            throw new ErrorException("Email is already exist");
        User userEntity = ToUserEntity(userDto);
        userEntity.setRoles("admin");
        userRep.save(userEntity);
    }

    public User registerStudent(UserDto userDto) throws ErrorException {
        if (Valids.isEmpty(userDto.getName())) throw new ErrorException("Name is empty");
        if (Valids.isEmpty(userDto.getGender()))
            throw new ErrorException("Gender is empty");
        if (Valids.isEmpty(userDto.getEmail())) throw new ErrorException("Email is empty");
        if (Valids.isEmpty(userDto.getPassword()))
            throw new ErrorException("Password is empty");
        if (userRep.existsByEmail(userDto.getEmail()))
            throw new ErrorException("Email is already exist");
        User userEntity = ToUserEntity(userDto);
        userEntity.setRoles("student");
        userRep.save(userEntity);

        return userEntity;
    }

    public void edit(User newInfor) throws ErrorException {
        User user = userRep.findFirstById(newInfor.getId());
        if (user == null) {
            throw new ErrorException("User not found");
        }
        if (!Valids.isEmpty(newInfor.getGender())) user.setGender(newInfor.getGender());
        if (!Valids.isEmpty(newInfor.getName())) user.setName(newInfor.getName());
        if (!Valids.isEmpty(newInfor.getPassword())) user.setPassword(newInfor.getPassword());

        if (!Valids.isEmpty(newInfor.getEmail())) {
            User userCheck = userRep.findFirstByEmail(newInfor.getEmail());
            if (userCheck != null && userCheck.getId() != user.getId())
                throw new ErrorException("Email is already exist");
            else user.setEmail(newInfor.getEmail());
        }
        userRep.save(user);
    }

    public void delete(int id) throws ErrorException {
        if (!userRep.existsById(id)) throw new ErrorException("Khong tim thay du lieu phu hop");
        userRep.deleteById(id);
    }
}