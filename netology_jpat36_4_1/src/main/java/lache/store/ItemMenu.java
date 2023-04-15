package lache.store;

public class ItemMenu {
    protected String item;
    protected int levelItem;
    protected int belongToItem;

    /*public ItemMenu(String item, int levelItem, int belongToItem) {
        this.item = item;
        this.levelItem = levelItem;
        this.belongToItem = belongToItem;
    }*/

    public String getItem() {
        return item;
    }

    public int getLevelItem() {
        return levelItem;
    }

    public int getBelongToItem() {
        return belongToItem;
    }

    @Override
    public String toString() {
        return item;
    }
}
