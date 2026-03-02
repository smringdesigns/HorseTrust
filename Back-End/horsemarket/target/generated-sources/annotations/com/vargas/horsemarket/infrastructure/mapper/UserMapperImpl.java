package com.vargas.horsemarket.infrastructure.mapper;

import com.vargas.horsemarket.domain.enums.UserRole;
import com.vargas.horsemarket.domain.model.User;
import com.vargas.horsemarket.infrastructure.persistence.entity.UserEntity;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-01T23:18:49-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toDomain(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( entity.getId() );
        user.username( entity.getUsername() );
        user.email( entity.getEmail() );
        user.password( entity.getPassword() );
        user.firstName( entity.getFirstName() );
        user.lastName( entity.getLastName() );
        user.phone( entity.getPhone() );
        user.location( entity.getLocation() );
        user.profileImageUrl( entity.getProfileImageUrl() );
        Set<UserRole> set = entity.getRoles();
        if ( set != null ) {
            user.roles( new LinkedHashSet<UserRole>( set ) );
        }
        user.verified( entity.isVerified() );
        user.active( entity.isActive() );
        user.verificationToken( entity.getVerificationToken() );
        if ( entity.getAverageRating() != null ) {
            user.averageRating( entity.getAverageRating().doubleValue() );
        }
        user.totalSales( entity.getTotalSales() );
        user.createdAt( entity.getCreatedAt() );
        user.updatedAt( entity.getUpdatedAt() );
        user.createdBy( entity.getCreatedBy() );
        user.updatedBy( entity.getUpdatedBy() );

        return user.build();
    }

    @Override
    public UserEntity toEntity(User domain) {
        if ( domain == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( domain.getId() );
        userEntity.username( domain.getUsername() );
        userEntity.email( domain.getEmail() );
        userEntity.password( domain.getPassword() );
        userEntity.firstName( domain.getFirstName() );
        userEntity.lastName( domain.getLastName() );
        userEntity.phone( domain.getPhone() );
        userEntity.location( domain.getLocation() );
        userEntity.profileImageUrl( domain.getProfileImageUrl() );
        Set<UserRole> set = domain.getRoles();
        if ( set != null ) {
            userEntity.roles( new LinkedHashSet<UserRole>( set ) );
        }
        userEntity.verified( domain.isVerified() );
        userEntity.active( domain.isActive() );
        userEntity.verificationToken( domain.getVerificationToken() );
        if ( domain.getAverageRating() != null ) {
            userEntity.averageRating( BigDecimal.valueOf( domain.getAverageRating() ) );
        }
        userEntity.totalSales( domain.getTotalSales() );

        return userEntity.build();
    }

    @Override
    public void updateEntityFromDomain(User domain, UserEntity entity) {
        if ( domain == null ) {
            return;
        }

        if ( domain.getCreatedAt() != null ) {
            entity.setCreatedAt( domain.getCreatedAt() );
        }
        if ( domain.getUpdatedAt() != null ) {
            entity.setUpdatedAt( domain.getUpdatedAt() );
        }
        if ( domain.getCreatedBy() != null ) {
            entity.setCreatedBy( domain.getCreatedBy() );
        }
        if ( domain.getUpdatedBy() != null ) {
            entity.setUpdatedBy( domain.getUpdatedBy() );
        }
        if ( domain.getId() != null ) {
            entity.setId( domain.getId() );
        }
        if ( domain.getUsername() != null ) {
            entity.setUsername( domain.getUsername() );
        }
        if ( domain.getEmail() != null ) {
            entity.setEmail( domain.getEmail() );
        }
        if ( domain.getPassword() != null ) {
            entity.setPassword( domain.getPassword() );
        }
        if ( domain.getFirstName() != null ) {
            entity.setFirstName( domain.getFirstName() );
        }
        if ( domain.getLastName() != null ) {
            entity.setLastName( domain.getLastName() );
        }
        if ( domain.getPhone() != null ) {
            entity.setPhone( domain.getPhone() );
        }
        if ( domain.getLocation() != null ) {
            entity.setLocation( domain.getLocation() );
        }
        if ( domain.getProfileImageUrl() != null ) {
            entity.setProfileImageUrl( domain.getProfileImageUrl() );
        }
        if ( entity.getRoles() != null ) {
            Set<UserRole> set = domain.getRoles();
            if ( set != null ) {
                entity.getRoles().clear();
                entity.getRoles().addAll( set );
            }
        }
        else {
            Set<UserRole> set = domain.getRoles();
            if ( set != null ) {
                entity.setRoles( new LinkedHashSet<UserRole>( set ) );
            }
        }
        entity.setVerified( domain.isVerified() );
        entity.setActive( domain.isActive() );
        if ( domain.getVerificationToken() != null ) {
            entity.setVerificationToken( domain.getVerificationToken() );
        }
        if ( domain.getAverageRating() != null ) {
            entity.setAverageRating( BigDecimal.valueOf( domain.getAverageRating() ) );
        }
        if ( domain.getTotalSales() != null ) {
            entity.setTotalSales( domain.getTotalSales() );
        }
    }
}
