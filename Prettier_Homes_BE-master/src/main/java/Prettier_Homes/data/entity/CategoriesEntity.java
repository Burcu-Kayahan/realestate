package Prettier_Homes.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "categories")
public class CategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String icon;

    @Column(name = "built_in", nullable = false, updatable = false, insertable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean builtIn;

    @NotNull
    private Integer seq;

    @NotNull
    private String slug;

    @NotNull
    @Column(name = "is_active", nullable = false,   columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isActive;

     @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


}
