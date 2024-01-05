package Prettier_Homes.data.repository;

import Prettier_Homes.data.entity.CategoriesEntity;
import Prettier_Homes.data.entity.ContactsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ContactsRepository extends JpaRepository<ContactsEntity,Long > {

}
