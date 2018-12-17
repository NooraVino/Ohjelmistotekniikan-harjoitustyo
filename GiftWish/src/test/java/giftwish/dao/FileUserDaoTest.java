/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giftwish.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import nooran.giftwish.dao.FileUserDao;
import nooran.giftwish.dao.UserDao;
import nooran.giftwish.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author vino
 */
public class FileUserDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    File userFile;
    UserDao dao;

    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");

        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("testaaja;salasana\n");
        }

        dao = new FileUserDao(userFile.getAbsolutePath());
    }

    @Test
    public void usersAreReadCorrectlyFromFile() {
        List<User> users = dao.getAll();
        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals("testaaja", user.getUserName());
        assertEquals("salasana", user.getPassword());
    }

    @Test
    public void existingUserIsFound() {
        User user = dao.findByUsername("testaaja");
        assertEquals("testaaja", user.getUserName());
        assertEquals("salasana", user.getPassword());
    }

    @Test
    public void nonExistingUserIsFound() {
        User user = dao.findByUsername("testihenkilo");
        assertEquals(null, user);
    }

    @Test
    public void savedUserIsFound() throws Exception {
        User nawUser = new User("kokeiluhenkilo", "koesana");
        dao.create(nawUser);

        User user = dao.findByUsername("kokeiluhenkilo");
        assertEquals("kokeiluhenkilo", user.getUserName());
        assertEquals("koesana", user.getPassword());
    }

    @After
    public void tearDown() {
        userFile.delete();
    }

}
