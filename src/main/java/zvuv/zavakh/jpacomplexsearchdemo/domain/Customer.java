package zvuv.zavakh.jpacomplexsearchdemo.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import zvuv.zavakh.jpacomplexsearchdemo.specifications.FilteringTypes;
import zvuv.zavakh.jpacomplexsearchdemo.specifications.FilteringOperation;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar", nullable = false, updatable = false)
    @FilteringOperation(
            types = {
                    FilteringTypes.EQUALS, FilteringTypes.LIKE,
                    FilteringTypes.IN, FilteringTypes.NOT_EQUAL
            }
    )
    private UUID id;

    @FilteringOperation(
            types = {
                    FilteringTypes.EQUALS, FilteringTypes.LIKE,
                    FilteringTypes.IN, FilteringTypes.NOT_EQUAL
            }
    )
    @Column(name = "first_name", length = 100, columnDefinition = "varchar")
    private String firstName;

    @FilteringOperation(
            types = {
                    FilteringTypes.EQUALS, FilteringTypes.LIKE,
                    FilteringTypes.IN, FilteringTypes.NOT_EQUAL
            }
    )
    @Column(name = "last_name", length = 100, columnDefinition = "varchar")
    private String lastName;

    @FilteringOperation(
            types = {
                    FilteringTypes.EQUALS, FilteringTypes.NOT_EQUAL, FilteringTypes.IN,
                    FilteringTypes.GREATER_THEN, FilteringTypes.GREATER_THEN_OR_EQUAL_TO,
                    FilteringTypes.LESS_THEN, FilteringTypes.LESS_THEN_OR_EQUAL_TO
            }
    )
    @Column(name = "age")
    private Integer age;

    @FilteringOperation(types = {FilteringTypes.EQUALS})
    @Column(name = "is_active")
    private Boolean isActive;

    @Version
    @Column(name = "version")
    private Long version;

    @FilteringOperation(
            types = {
                    FilteringTypes.EQUALS, FilteringTypes.NOT_EQUAL, FilteringTypes.IN,
                    FilteringTypes.GREATER_THEN, FilteringTypes.GREATER_THEN_OR_EQUAL_TO,
                    FilteringTypes.LESS_THEN, FilteringTypes.LESS_THEN_OR_EQUAL_TO
            }
    )
    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Timestamp createdDate;

    @FilteringOperation(
            types = {
                    FilteringTypes.EQUALS, FilteringTypes.NOT_EQUAL, FilteringTypes.IN,
                    FilteringTypes.GREATER_THEN, FilteringTypes.GREATER_THEN_OR_EQUAL_TO,
                    FilteringTypes.LESS_THEN, FilteringTypes.LESS_THEN_OR_EQUAL_TO
            }
    )
    @UpdateTimestamp
    private Timestamp modifiedDate;
}
