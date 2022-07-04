package com.avengers.rpgame.graphics.store;

import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.text.FontFactory;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class Store {
    private Array<Item> items = new Array<Item>();
    private Array<ScreenItem> screenItems;
    private Array<Item> itemsSelected = new Array<Item>();
    private Coin coin = new Coin(50, 50, 0.97f, 0.06f);
    private BitmapFont gameFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.generalHUDFontSize, Color.WHITE, false, Color.BLACK);
    private BitmapFont priceFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.storePriceFontSize, Color.WHITE, false, Color.BLACK);
    private int silverCoins;
    private int itemsType;
    private int actionType;
    private GameConfig gameConfig;
    private int itemSelected = 0;
    private Vector2 resolution;

    // temporal character values
    private int playerLevel = 34;
    private int characterClass = 2;

    public Store () {
        gameConfig = GameConfig.getInstance();
        this.setSilverCoins(0);
        this.setItemsType(0);
        this.setActionType(0);
        createTemporalItems();
        processConfiguration();
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public int getSilverCoins() {
        return silverCoins;
    }

    public void setSilverCoins(int silverCoins) {
        this.silverCoins = silverCoins;
    }

    public int getItemsType() {
        return itemsType;
    }

    public void setItemsType(int itemsType) {
        this.itemsType = itemsType;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public int getItemSelected() { return itemSelected; }

    public void setItemSelected(int itemSelected) { this.itemSelected = itemSelected; }

    public Array<Item> getItemsSelected() { return itemsSelected; }

    public void update (int silverCoins, int itemsType, int actionType, int itemSelected) {
        this.setSilverCoins(silverCoins);
        this.setItemsType(itemsType);
        this.setActionType(actionType);
        this.setItemSelected(itemSelected);
        this.processConfiguration();
    }

    public void updateItemSelected(int itemSelected) {
        this.setItemSelected(itemSelected);
        this.processConfiguration();
    }

    public void draw (SpriteBatch batch) {
        resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());

        coin.get_sprite().draw(batch);
        gameFont.draw(batch, String.valueOf(silverCoins), resolution.x*0.07f, resolution.y*0.97f);

        if (actionType != 0) {
            for(ScreenItem item: screenItems) {
                item.get_sprite().draw(batch);
                priceFont.draw(batch, String.valueOf(item.getItem().getPrice()), item.getItemX()+30, item.getItemY());
            }
        }

        if (this.itemSelected != 0) {
            this.drawItemInfo(batch, resolution);
        }
    }

    public void drawItemInfo(SpriteBatch batch, Vector2 resolution) {
        if (this.itemsSelected.size != 0) {
            Item tItem = this.itemsSelected.get(itemSelected-1);
            Coin tCoin = new Coin(30, 30, 0.62f, 0.55f);

            gameFont.draw(batch, String.valueOf(tItem.getNombre()), resolution.x*0.38f, resolution.y*0.50f);
            tCoin.get_sprite().draw(batch);
            gameFont.draw(batch, String.valueOf(tItem.getPrice()), resolution.x*0.40f, resolution.y*0.47f);

            GlyphLayout description = new GlyphLayout(priceFont, String.valueOf(tItem.getDescription()), Color.WHITE, 200, Align.left, true);
            priceFont.draw(batch, description, resolution.x*0.38f, resolution.y*0.44f);
        }
    }

    private void processConfiguration() {
        this.itemsSelected = new Array<Item>();
        if (this.actionType == 0) {
            for(Item tItem : this.items){
                if (tItem.getItemType() == this.itemsType && tItem.getLevelRequired() <= this.playerLevel) {
                    this.itemsSelected.add(tItem);
                }
            }
            this.createItems(this.itemsSelected);
        } else if (this.actionType == 1) {
            for(Item tItem : this.items){
                if (this.characterClass == 2) {
                    if (tItem.getItemType() == this.itemsType && tItem.getLevelRequired() <= this.playerLevel) {
                        this.itemsSelected.add(tItem);
                    }
                } else {
                    if (tItem.getItemType() == this.itemsType && tItem.getItemType() != 3 && tItem.getLevelRequired() <= this.playerLevel) {
                        this.itemsSelected.add(tItem);
                    }
                }
            }
            this.createItems(this.itemsSelected);
        } else if (this.actionType == 2) {
            for(Item tItem : this.items){
                this.itemsSelected.add(tItem);
            }
            this.createItems(this.itemsSelected);
        }
    }

    private void createItems(Array<Item> currentItems) {
        this.screenItems = new Array<ScreenItem>();
        double lastValueX = 0.915;
        double lastValueY = 0.23;
        int lastItem = 0;

        for (int i = 0; i < currentItems.size; i++) {
            Item tItem = currentItems.get(i);
            ScreenItem item;
            if (this.itemSelected == i + 1) {
                item = new ScreenItem(tItem, lastValueX, lastValueY, 100);
            } else {
                item = new ScreenItem(tItem, lastValueX, lastValueY);
            }
            screenItems.add(item);
            lastValueX -= 0.045;
            lastItem++;
            if (lastItem >= 5) {
                lastValueX = 0.915;
                lastValueY += 0.12;
                lastItem = 0;
            }
        }
    }

    // Temporal array items creator
    private void createTemporalItems() {
        for(int i=0; i < 30; i++) {
            Item currentItem = null;
            int itemType = (int)(Math.random()*3+0);
            int price = (int)(Math.random()*500+25);
            int levelRequired = (int)(Math.random()*22+1);

            if (itemType == 0) {
                currentItem = new Item(i, "Armadura " + String.valueOf(i+1), "Description Armadura", Resources.potion, price, itemType, levelRequired);
            } else if (itemType == 1) {
                currentItem = new Item(i, "Joyeria " + String.valueOf(i+1), "Description Joyeria magica", Resources.potion, price, itemType, levelRequired);
            } else if (itemType == 2) {
                currentItem = new Item(i, "Arma " + String.valueOf(i+1), "Description Arma", Resources.sword, price, itemType, levelRequired);
            } else if (itemType == 3) {
                currentItem = new Item(i, "Pocion " + String.valueOf(i+1), "Description Pocion", Resources.potion, price, itemType, levelRequired);
            }

            this.items.add(currentItem);
        }
        System.out.println(this.items);
    }
}
