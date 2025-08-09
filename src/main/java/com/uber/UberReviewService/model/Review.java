package com.uber.UberReviewService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
Inheritance in Jpa:

    @MappedSuperclass --> for parent class
      -> Using the MappedSuperclass strategy, inheritance is only evident in the class but not the entity model.

    Single Table
        -> The Single Table strategy creates one table for each class hierarchy.
           JPA also chooses this strategy by default if we don’t specify one explicitly.
        -> This strategy has the advantage of polymorphic query performance since only one table needs to be accessed
           when querying parent entities.
        -> On the other hand, this also means that we can no longer use NOT NULL constraints on subclass entity
           properties.
        -> Discriminator Values
                -> Since the records for all entities will be in the same table, Hibernate needs a way to differentiate
                   between them.
                -> By default, this is done through a discriminator column called DTYPE that has the name of the entity
                   as a value.
                -> To customize the discriminator column, we can use the @DiscriminatorColumn annotation.
                -> Hibernate adds two other predefined values that the annotation can take — null and not null:
                    -> @DiscriminatorValue(“null”) means that any row without a discriminator value will be mapped to
                       the entity class with this annotation; this can be applied to the root class of the hierarchy.
                    -> @DiscriminatorValue(“not null”) – Any row with a discriminator value not matching any of the ones
                       associated with entity definitions will be mapped to the class with this annotation.

    Table Per Class
       -> The Table per Class strategy maps each entity to its table, which contains all the properties of the entity,
          including the ones inherited.
       -> The resulting schema is similar to the one using @MappedSuperclass. But Table per Class will indeed define
          entities for parent classes, allowing associations and polymorphic queries as a result.
       -> This is not that different from merely mapping each entity without inheritance. The distinction is clear when
          querying the base class, which will return all the subclass records as well by using a UNION statement in the
          background.
       -> The use of UNION can also lead to inferior performance when choosing this strategy. Another issue is that we
          can no longer use identity key generation.
       -> Also it causes redundancy.

    Joined Table
       -> Using this strategy, each class in the hierarchy is mapped to its table. The only column that repeatedly
           appears in all the tables is the identifier, which will be used for joining them when needed.
       -> The disadvantage of this inheritance mapping method is that retrieving entities requires joins between
           tables, which can result in lower performance for large numbers of records.
       -> The number of joins is higher when querying the parent class because it will join with every single related
           child — so performance is more likely to be affected the higher up the hierarchy we want to retrieve records.

*/

@Entity
@Table(name = "booking_review")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Review extends BaseModel {

    private Double rating;
    private String comment;

    @Override
    public String toString() {
        return "Review [rating=" + rating + ", content=" + comment + "]" + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }
}
