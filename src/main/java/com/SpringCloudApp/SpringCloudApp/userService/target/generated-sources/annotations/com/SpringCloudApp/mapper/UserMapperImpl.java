package com.SpringCloudApp.mapper;

import com.SpringCloudApp.dto.excel.ExcelFieldDTO;
import com.SpringCloudApp.dto.excel.ExcelSchemaDTO;
import com.SpringCloudApp.dto.request.UserRequest;
import com.SpringCloudApp.model.ExcelField;
import com.SpringCloudApp.model.ExcelSchema;
import com.SpringCloudApp.model.Users;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-05T09:29:50+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserRequest UserToUserRequest(Users user) {
        if ( user == null ) {
            return null;
        }

        UserRequest userRequest = new UserRequest();

        userRequest.setId( user.getId() );
        userRequest.setFirstname( user.getFirstname() );
        userRequest.setLastname( user.getLastname() );
        userRequest.setAge( user.getAge() );
        userRequest.setAge2( user.getAge2() );
        userRequest.setExcelSchemaList( excelSchemaListToExcelSchemaDTOList( user.getExcelSchemaList() ) );
        userRequest.setDeneme( user.getDeneme() );
        userRequest.setCreated( user.getCreated() );
        userRequest.setUpdated( user.getUpdated() );

        return userRequest;
    }

    @Override
    public Users UserRequestToUser(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        Users users = new Users();

        users.setId( userRequest.getId() );
        users.setFirstname( userRequest.getFirstname() );
        users.setLastname( userRequest.getLastname() );
        users.setAge( userRequest.getAge() );
        users.setExcelSchemaList( excelSchemaDTOListToExcelSchemaList( userRequest.getExcelSchemaList() ) );
        users.setDeneme( userRequest.getDeneme() );
        users.setAge2( userRequest.getAge2() );
        users.setCreated( userRequest.getCreated() );
        users.setUpdated( userRequest.getUpdated() );

        return users;
    }

    @Override
    public void updateUserToUserRequest(UserRequest userRequest, Users user) {
        if ( userRequest == null ) {
            return;
        }

        user.setFirstname( userRequest.getFirstname() );
        user.setLastname( userRequest.getLastname() );
        user.setAge( userRequest.getAge() );
        if ( user.getExcelSchemaList() != null ) {
            List<ExcelSchema> list = excelSchemaDTOListToExcelSchemaList( userRequest.getExcelSchemaList() );
            if ( list != null ) {
                user.getExcelSchemaList().clear();
                user.getExcelSchemaList().addAll( list );
            }
            else {
                user.setExcelSchemaList( null );
            }
        }
        else {
            List<ExcelSchema> list = excelSchemaDTOListToExcelSchemaList( userRequest.getExcelSchemaList() );
            if ( list != null ) {
                user.setExcelSchemaList( list );
            }
        }
        user.setDeneme( userRequest.getDeneme() );
        user.setAge2( userRequest.getAge2() );
        user.setCreated( userRequest.getCreated() );
        user.setUpdated( userRequest.getUpdated() );
    }

    @Override
    public List<UserRequest> userListToUserRequestList(List<Users> users) {
        if ( users == null ) {
            return null;
        }

        List<UserRequest> list = new ArrayList<UserRequest>( users.size() );
        for ( Users users1 : users ) {
            list.add( UserToUserRequest( users1 ) );
        }

        return list;
    }

    protected ExcelFieldDTO excelFieldToExcelFieldDTO(ExcelField excelField) {
        if ( excelField == null ) {
            return null;
        }

        ExcelFieldDTO excelFieldDTO = new ExcelFieldDTO();

        excelFieldDTO.setId( excelField.getId() );
        excelFieldDTO.setField( excelField.getField() );
        excelFieldDTO.setColumnName( excelField.getColumnName() );
        excelFieldDTO.setIndex( excelField.getIndex() );
        excelFieldDTO.setColumnType( excelField.getColumnType() );
        excelFieldDTO.setRequired( excelField.isRequired() );

        return excelFieldDTO;
    }

    protected List<ExcelFieldDTO> excelFieldListToExcelFieldDTOList(List<ExcelField> list) {
        if ( list == null ) {
            return null;
        }

        List<ExcelFieldDTO> list1 = new ArrayList<ExcelFieldDTO>( list.size() );
        for ( ExcelField excelField : list ) {
            list1.add( excelFieldToExcelFieldDTO( excelField ) );
        }

        return list1;
    }

    protected ExcelSchemaDTO excelSchemaToExcelSchemaDTO(ExcelSchema excelSchema) {
        if ( excelSchema == null ) {
            return null;
        }

        ExcelSchemaDTO excelSchemaDTO = new ExcelSchemaDTO();

        if ( excelSchema.getId() != null ) {
            excelSchemaDTO.setId( excelSchema.getId().intValue() );
        }
        excelSchemaDTO.setColumns( excelFieldListToExcelFieldDTOList( excelSchema.getColumns() ) );

        return excelSchemaDTO;
    }

    protected List<ExcelSchemaDTO> excelSchemaListToExcelSchemaDTOList(List<ExcelSchema> list) {
        if ( list == null ) {
            return null;
        }

        List<ExcelSchemaDTO> list1 = new ArrayList<ExcelSchemaDTO>( list.size() );
        for ( ExcelSchema excelSchema : list ) {
            list1.add( excelSchemaToExcelSchemaDTO( excelSchema ) );
        }

        return list1;
    }

    protected ExcelField excelFieldDTOToExcelField(ExcelFieldDTO excelFieldDTO) {
        if ( excelFieldDTO == null ) {
            return null;
        }

        ExcelField excelField = new ExcelField();

        excelField.setId( excelFieldDTO.getId() );
        excelField.setIndex( excelFieldDTO.getIndex() );
        excelField.setField( excelFieldDTO.getField() );
        excelField.setColumnName( excelFieldDTO.getColumnName() );
        excelField.setColumnType( excelFieldDTO.getColumnType() );
        excelField.setRequired( excelFieldDTO.isRequired() );

        return excelField;
    }

    protected List<ExcelField> excelFieldDTOListToExcelFieldList(List<ExcelFieldDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ExcelField> list1 = new ArrayList<ExcelField>( list.size() );
        for ( ExcelFieldDTO excelFieldDTO : list ) {
            list1.add( excelFieldDTOToExcelField( excelFieldDTO ) );
        }

        return list1;
    }

    protected ExcelSchema excelSchemaDTOToExcelSchema(ExcelSchemaDTO excelSchemaDTO) {
        if ( excelSchemaDTO == null ) {
            return null;
        }

        ExcelSchema excelSchema = new ExcelSchema();

        excelSchema.setId( (long) excelSchemaDTO.getId() );
        excelSchema.setColumns( excelFieldDTOListToExcelFieldList( excelSchemaDTO.getColumns() ) );

        return excelSchema;
    }

    protected List<ExcelSchema> excelSchemaDTOListToExcelSchemaList(List<ExcelSchemaDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ExcelSchema> list1 = new ArrayList<ExcelSchema>( list.size() );
        for ( ExcelSchemaDTO excelSchemaDTO : list ) {
            list1.add( excelSchemaDTOToExcelSchema( excelSchemaDTO ) );
        }

        return list1;
    }
}
