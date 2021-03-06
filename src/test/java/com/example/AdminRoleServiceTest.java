package com.example;

import com.example.dao.UserRepository;
import com.example.dao.UserRoleRepositiry;
import com.example.exception.UserHasMoreThatOneRoleException;
import com.example.model.UserRole;
import com.example.model.UsersModel;
import com.example.service.adminService.AdminRoleService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminRoleServiceTest {

    @InjectMocks
    private AdminRoleService sut = new AdminRoleService();

    @Mock
    private UserRepository userRepositiry;

    @Mock
    private UserRoleRepositiry userRoleRepositiry;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void prepareOurMocks() {
        UsersModel usersModelAlex = new UsersModel();
        usersModelAlex.setName("Alex");
        usersModelAlex.setUserRoles(new ArrayList<>());

        when(userRepositiry.findOne(eq("Alex"))).thenReturn(usersModelAlex);

        UsersModel usersModelTest = new UsersModel();
        usersModelTest.setName("test");
        usersModelTest.setUserRoles(new ArrayList<>());


        List<UsersModel> all = new ArrayList<>();
        all.add(usersModelAlex);
        all.add(usersModelTest);
        when(userRepositiry.findAll()).thenReturn(all);


        when(userRepositiry.findOne(eq("test"))).thenReturn(usersModelTest);
    }
@Test
    public void testRole() {
    expectedException.expect(UserHasMoreThatOneRoleException.class);
        List<String> roleUserMapping = new ArrayList<>();
        roleUserMapping.add("Alex_admin");
        roleUserMapping.add("test_admin");
        roleUserMapping.add("test_user");

       sut.addRightsAdmin(roleUserMapping);

    }


    @Test
    public void testAddAdminRights() {
        List<String> roleUserMapping = new ArrayList<>();
        roleUserMapping.add("Alex_admin");
        roleUserMapping.add("test_user");

        sut.addRightsAdmin(roleUserMapping);

        verify(userRepositiry, times(2)).save(any(UsersModel.class));
    }

}

