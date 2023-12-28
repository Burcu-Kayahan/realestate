package Prettier_Homes.service.Impl;


import Prettier_Homes.data.entity.RoleEntity;
import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.data.repository.RoleRepository;
import Prettier_Homes.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DefaultDataService implements CommandLineRunner {

     @Autowired
    UserRepository userRepository;

    @Autowired
     RoleRepository roleRepository;



    //category default degerler eklenmeli




    public void saveDeafultData(){
        UserEntity user = new UserEntity();
        user.setEmail("admin@gmail.com");
        user.setPasswordHash("$2a$10$0Yp3EDsmE13PbGLp2.fWTev8Uekp8dVaeOYMTfHnlZNjdezttUCJe");
        user.setResetPasswordCode("$2a$10$0Yp3EDsmE13PbGLp2.fWTev8Uekp8dVaeOYMTfHnlZNjdezttUCJe");
        user.setLastName("lastName");
        user.setFirstName("firstName");
        user.setPhone("1234567890");
        user.setCreateAt(LocalDateTime.now());
        user.setUpdateAt(LocalDateTime.now());
        user.setBuiltIn(true);
        user.setActive(true);
        Optional<UserEntity> existUser = Optional.ofNullable(userRepository.findByEmail("admin@gmail.com"));

        UserEntity user2 = new UserEntity();
        if (existUser.isEmpty()) {
            user2 = userRepository.save(user);
        }
        List<String> roles = new ArrayList<>();
        roles.add("CUSTOMER");
        roles.add("MANAGER");
        roles.add("ADMIN");



      for (int i =0; i<3; i++){
          Optional<RoleEntity> existRole = Optional.ofNullable(roleRepository.findByName(roles.get(i)));
          if(existRole.isEmpty()) {
              RoleEntity role= new RoleEntity();
              role.setName(roles.get(i));
              roleRepository.save(role);
              System.out.println("Role : "+roles.get(i)+" is created");
          }
      }
        System.out.println("All Roles are created : `ADMIN, MANAGER, CUSTOMER");
     RoleEntity existRole = roleRepository.findByName("ADMIN");

        if(existUser.isEmpty()){
            user2.setRoles(existRole);
            userRepository.save(user2);
            System.out.println("New Admin Created. email : admin@gmail.com  password : 123456789");
        }
        else{
            existUser.get().setRoles(existRole);
            userRepository.save(existUser.get());
            System.out.println("Admin updated.  email : admin@gmail.com  password : 123456789");
        }
    }

    public void createCustomerAndManager() {
        UserEntity customer = new UserEntity();
        Optional<UserEntity> existUser = Optional.ofNullable(userRepository.findByEmail("customer@gmail.com"));
        if (existUser.isEmpty()) {
            customer.setFirstName("Customer");
            customer.setLastName("LastName");
            customer.setEmail("customer@gmail.com");
            customer.setPasswordHash("$2a$10$0Yp3EDsmE13PbGLp2.fWTev8Uekp8dVaeOYMTfHnlZNjdezttUCJe");
            customer.setResetPasswordCode("$2a$10$0Yp3EDsmE13PbGLp2.fWTev8Uekp8dVaeOYMTfHnlZNjdezttUCJe");
            customer.setPhone("1234567890");
            customer.setCreateAt(LocalDateTime.now());
            customer.setUpdateAt(LocalDateTime.now());
            customer.setBuiltIn(false);
            customer.setActive(true);
            UserEntity customer2= userRepository.save(customer);
            RoleEntity customerRole = roleRepository.findByName("CUSTOMER");
            customer2.setRoles(customerRole);
            userRepository.save(customer2);
            System.out.println("New customer created : customer@gmail.com, password : 123456789");
        }
        Optional<UserEntity> existManager = Optional.ofNullable(userRepository.findByEmail("manager@gmail.com"));
        UserEntity manager = new UserEntity();
        if (existManager.isEmpty()) {
            manager.setFirstName("Customer");
            manager.setLastName("LastName");
            manager.setEmail("manager@gmail.com");
            manager.setPasswordHash("$2a$10$0Yp3EDsmE13PbGLp2.fWTev8Uekp8dVaeOYMTfHnlZNjdezttUCJe");
            manager.setResetPasswordCode("$2a$10$0Yp3EDsmE13PbGLp2.fWTev8Uekp8dVaeOYMTfHnlZNjdezttUCJe");
            manager.setPhone("1234567890");
            manager.setCreateAt(LocalDateTime.now());
            manager.setUpdateAt(LocalDateTime.now());
            manager.setBuiltIn(false);
            manager.setActive(true);
            UserEntity manager2=    userRepository.save(manager);
            RoleEntity managerRole = roleRepository.findByName("MANAGER");
            manager2.setRoles(managerRole);
            userRepository.save(manager2);
            System.out.println("New manager created : manager@gmail.com, password : 123456789");
        }

    }


// category eklenecek
    @Override
    public void run(String... args) throws Exception {
        saveDeafultData();
        createCustomerAndManager();

    }
}
