/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giftwish.dao;

import giftwish.domain.FakeUserDao;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import nooran.giftwish.dao.FileGiftDao;
import nooran.giftwish.dao.GiftDao;
import nooran.giftwish.dao.UserDao;
import nooran.giftwish.domain.Gift;
import nooran.giftwish.domain.User;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author vino
 */
public class FileGiftDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    File userFile;
    GiftDao dao;

    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");
        UserDao userDao = new FakeUserDao();
        userDao.create(new User("testaaja", "testisana"));

        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("1;laukku;nahkainen;false;testaaja\n");
        }

        dao = new FileGiftDao(userFile.getAbsolutePath(), userDao);
    }

    @Test
    public void GiftsAreReadCorrectlyFromFile() {
        List<Gift> gifts = dao.getAll();
        assertEquals(1, gifts.size());
        Gift gift = gifts.get(0);
        assertEquals("nahkainen", gift.getContent());
        assertFalse(gift.isDone());
        assertEquals(1, gift.getId());
        assertEquals("testaaja", gift.getUser().getUserName());
    }

    @Test
    public void GiftsCanBeSetDone() throws Exception {
        dao.setDone(1);
        Gift gift = dao.getAll().get(0);
        assertTrue(gift.isDone());
    }

    @Test
    public void createdTodosAreListed() throws Exception {
        dao.create(new Gift("kirja", "Aapinen", new User("testihenkilo", "testisana")));

        List<Gift> gifts = dao.getAll();
        assertEquals(2, gifts.size());
        Gift gift = gifts.get(1);
        assertEquals("Aapinen", gift.getContent());
        assertFalse(gift.isDone());
        assertNotEquals(1, gift.getId());
        assertEquals("testihenkilo", gift.getUser().getUserName());
    }

    @After
    public void tearDown() {
        userFile.delete();
    }

}
