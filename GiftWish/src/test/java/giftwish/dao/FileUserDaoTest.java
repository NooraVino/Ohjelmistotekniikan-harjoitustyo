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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
            file.write("testertester;Teppo Testaaja\n");
        }
        
        dao = new FileUserDao(userFile.getAbsolutePath());
    }
   
//    @Test
//    public void usersAreReadCorrectlyFromFile() {
//        List<User> users = dao.getAll();
//        assertEquals(1, users.size());
//        User user = users.get(0);
//        assertEquals("Teppo Testaaja", user.getUserName());
//        assertEquals("testertester", user.getUserName());
//    }
//    
//    @Test
//    public void existingUserIsFound() {
//        User user = dao.findByUsername("testertester");
//        assertEquals("Teppo Testaaja", user.getUserName());
//        assertEquals("testertester", user.getUserName());
//    }
    
    @Test
    public void nonExistingUserIsFound() {
        User user = dao.findByUsername("Noora");
        assertEquals(null, user);
    }
  
//    @Test
//    public void savedUserIsFound() throws Exception {
//        User nawUser = new User("hellas", "Arto Hellas");
//        dao.create(nawUser);
//        
//        User user = dao.findByUsername("hellas");
//        assertEquals("Arto Hellas", user.getUserName());
//        assertEquals("hellas", user.getUserName());
//    }
    
    @After
    public void tearDown() {
        userFile.delete();
    }
   
}
