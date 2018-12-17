package giftwish.domain;

import java.util.ArrayList;
import java.util.List;
import nooran.giftwish.dao.GiftDao;
import nooran.giftwish.domain.Gift;

/**
 *
 * @author vino
 */
public class FakeGiftDao implements GiftDao {

    List<Gift> gifts;

    public FakeGiftDao() {
        gifts = new ArrayList<>();
    }

    @Override
    public List<Gift> getAll() {
        return gifts;
    }

    @Override
    public Gift create(Gift gift) {
        gift.setId(gifts.size() + 1);
        gifts.add(gift);
        return gift;
    }

    @Override
    public void setDone(int id) {
        for (Gift g : gifts) {
            if (g.getId() == id) {
                g.setDone();
            }
        }
    }

    @Override
    public void remake(int id, String name, String content) throws Exception {
        for (Gift g : gifts) {
            if (g.getId() == id) {
                g.setName(name);
                g.setContent(content);
            }

        }
    }

}
