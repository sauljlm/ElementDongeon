package com.avengers.rpgame.graphics.store;

import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.text.FontFactory;
import com.avengers.rpgame.graphics.text.Text;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.avengers.rpgame.logic.entities.Item;

public class Store {
    private Array<Item> items = new Array<Item>();
    private Array<ScreenItem> screenItems;
    private Array<Item> itemsSelected = new Array<Item>();
    private Coin coin = new Coin(50, 50, 0.97f, 0.06f);
    private SpeechBubble speechBubble = new SpeechBubble(350, 250, 0.36f, 0.34f);
    private AbstractCharacter character;
    private BitmapFont gameFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.generalHUDFontSize, Color.WHITE, false, Color.BLACK);
    private BitmapFont characterCoinsFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.generalHUDFontSize, Color.WHITE, false, Color.BLACK);
    private BitmapFont priceFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.storePriceFontSize, Color.WHITE, false, Color.BLACK);
    private int itemsType;
    private int actionType;
    private GameConfig gameConfig;
    private int itemSelected = 0;
    private Vector2 resolution;
    private String message = "";

    public Store (AbstractCharacter character) {
        gameConfig = GameConfig.getInstance();
        this.setCharacter(character);
        this.setItemsType(0);
        this.setActionType(0);
        createTemporalItems();
        processConfiguration();
    }

    public int getItemsType() {
        return itemsType;
    }

    public void setItemsType(int itemsType) {
        this.itemsType = itemsType;
    }

    public AbstractCharacter getCharacter() { return character; }

    public void setCharacter(AbstractCharacter character) { this.character = character; }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType+1;
    }

    public int getItemSelected() { return itemSelected; }

    public void setItemSelected(int itemSelected) { this.itemSelected = itemSelected; }

    public Array<Item> getItemsSelected() { return itemsSelected; }

    public void update (int itemsType, int actionType, int itemSelected) {
        this.setItemsType(itemsType);
        this.setActionType(actionType);
        this.setItemSelected(itemSelected);
        this.setActionMessage();
        this.processConfiguration();
    }

    public void updateItemSelected(int itemSelected) {
        this.setItemSelected(itemSelected);
        this.setActionMessage();
        this.processConfiguration();
    }

    public void draw (SpriteBatch batch) {
        resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());

        coin.get_sprite().draw(batch);
        characterCoinsFont.draw(batch, String.valueOf(this.character.getCoins()), resolution.x*0.07f, resolution.y*0.97f);

        if (actionType != 0) {
            for(ScreenItem item: screenItems) {
                item.get_sprite().draw(batch);
                priceFont.draw(batch, String.valueOf(item.getItem().getPrice()), item.getItemX()+30, item.getItemY());
            }
        }

        if (this.itemSelected != 0) {
            this.drawItemInfo(batch, resolution);
            this.showMessage(batch);
        }
    }

    public void drawItemInfo(SpriteBatch batch, Vector2 resolution) {
        if (this.itemsSelected.size != 0) {
            Item tItem = this.itemsSelected.get(itemSelected-1);
            Coin tCoin = new Coin(30, 30, 0.62f, 0.55f);

            gameFont.draw(batch, String.valueOf(tItem.getName()), resolution.x*0.38f, resolution.y*0.50f);
            tCoin.get_sprite().draw(batch);
            gameFont.draw(batch, String.valueOf(tItem.getPrice()), resolution.x*0.40f, resolution.y*0.47f);

            GlyphLayout description = new GlyphLayout(priceFont, String.valueOf(tItem.getDescription()), Color.WHITE, 200, Align.left, true);
            priceFont.draw(batch, description, resolution.x*0.38f, resolution.y*0.44f);
        }
    }

    public void showMessage(SpriteBatch batch) {
        speechBubble.get_sprite().draw(batch);

        GlyphLayout textMessage = new GlyphLayout(gameFont, String.valueOf(this.message), Color.BLACK, 250, Align.left, true);
        gameFont.draw(batch, textMessage, resolution.x*0.67f, resolution.y*0.85f);
    }

    public boolean buyItem() {
        Item tItem = this.itemsSelected.get(itemSelected-1);
        boolean completed = false;
        boolean bought = false;
        
        for(Item characterItem : this.character.getItems()){
            if (characterItem.getId() == tItem.getId())
                bought = true;
        }
        
        if (!bought) {
            this.character.addNewItem(tItem);
            this.character.setCoins(this.character.getCoins() - tItem.getPrice());
            completed = true;
        } else {
            completed = false;
        }

        return completed;
    }

    public boolean sellItem() {
        Item tItem = this.itemsSelected.get(itemSelected-1);
        boolean completed = false;

        boolean found = false;
        int itemId = 0;
        for(Item characterItem : this.character.getItems()){
            if (characterItem.getId() == tItem.getId())
                itemId = tItem.getId();
                found = true;
        }

        if (found) {
            this.character.deleteItem(itemId);
            this.character.setCoins(this.character.getCoins() + tItem.getPrice());
            this.processConfiguration();
            completed = true;
        } else {
            completed = false;
        }

        return completed;
    }

    public void changeCoinsColor() {
        if (checkPurchase()) {
            this.characterCoinsFont.setColor(Color.WHITE);
        } else {
            this.characterCoinsFont.setColor(Color.RED);
        }
    }

    public boolean checkPurchase() {
        boolean check = false;
        Item tItem = this.itemsSelected.get(itemSelected-1);

        if (this.character.getCoins() >= tItem.getPrice()) {
            check = true;
        }

        return check;
    }

    public void setActionMessage() {
        if (this.actionType == 1) {
            this.message = "Presione C para comprar";
        } else if (this.actionType == 2){
            this.message = "Presione V para vender";
        }

    }

    public void setConfirmationMessage(boolean done) {
        if (this.actionType == 1) {
            if (done) {
                this.message = "Compra realizada con exito";
            } else {
                this.message = "La compra no se realizo";
            }
        } else if (this.actionType == 2) {
            if (done) {
                this.message = "Venta realizada con exito";
            } else {
                this.message = "La venta no se realizo";
            }
        }
    }

    public void cleanMessage() {
        this.message = "";
    }

    private void processConfiguration() {

        this.itemsSelected = new Array<Item>();
        if(this.actionType == 1) {
            for(Item tItem : this.items){
                if (this.character.getCharacterClass().getIdCharacterClass() == 3) {
                    if (tItem.getItemType() == this.itemsType && tItem.getUnlockLevel() <= this.character.getLevel()) {
                        this.itemsSelected.add(tItem);
                    }
                } else {
                    if (tItem.getItemType() == this.itemsType && tItem.getItemType() != 3 && tItem.getUnlockLevel() <= this.character.getLevel()) {
                        this.itemsSelected.add(tItem);
                    }
                }
            }
            this.createItems(this.itemsSelected);
        } else if (this.actionType == 2) {
            for(Item tItem : this.character.getItems()){
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
            int levelRequired = (int)(Math.random()*2+1);

            if (itemType == 0) {
                currentItem = new Item (i + this.character.getItems().size(), "Armadura","Da 50 puntos de vida", price, levelRequired, Resources.potion, itemType, 0,0,0,0,0,0,50);
            } else if (itemType == 1) {
                currentItem = new Item (i + this.character.getItems().size(), "Joyeria","Description Joyeria magica", price, levelRequired, Resources.potion, itemType, 0,0,0,0,0,0,50);
            } else if (itemType == 2) {
                currentItem = new Item (i + this.character.getItems().size(), "Arma","Description Arma", price, levelRequired, Resources.sword, itemType, 0,0,0,0,0,0,50);
            } else if (itemType == 3) {
                currentItem = new Item (i + this.character.getItems().size(), "Pocion","Description Pocion", price, levelRequired, Resources.potion, itemType, 0,0,0,0,0,0,50);
            }
            this.items.add(currentItem);
        }
    }
}
