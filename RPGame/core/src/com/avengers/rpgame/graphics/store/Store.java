package com.avengers.rpgame.graphics.store;

import com.avengers.rpgame.audio.SoundEffectsManager;
import com.avengers.rpgame.data.dataStorage.ProxyDataManager;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.text.FontFactory;
import com.avengers.rpgame.logic.entities.Attack;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Skill;
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
import com.sun.tools.sjavac.server.Sjavac;

import java.util.ArrayList;

import static com.avengers.rpgame.utils.Resources.coinSound;

public class Store {

    private Array<ScreenItem> screenItems;
    private Array<Item> itemsSelected = new Array<Item>();
    private ArrayList<Attack> sourceAttaks = new ArrayList<Attack>();
    private ArrayList<Skill> sourceSkills = new ArrayList<Skill>();
    private ArrayList<Item>sourceItems = new ArrayList<Item>();
    private Coin coin = new Coin(50, 50, 0.97f, 0.06f);
    private SpeechBubble speechBubble = new SpeechBubble(350, 250, 0.36f, 0.34f);
    private ProxyDataManager proxyDataManager;
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
        proxyDataManager = new ProxyDataManager();
        this.setCharacter(character);
        this.setItemsType(0);
        this.setActionType(0);
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
        if (this.itemsSelected.size > 0) {
            Item tItem = this.itemsSelected.get(itemSelected-1);
            Coin tCoin = new Coin(30, 30, 0.62f, 0.63f);

            // gameFont.draw(batch, String.valueOf(tItem.getDescription()), resolution.x*0.38f, resolution.y*0.50f);
            tCoin.get_sprite().draw(batch);
            gameFont.draw(batch, String.valueOf(tItem.getPrice()), resolution.x*0.40f, resolution.y*0.39f);

            GlyphLayout description = new GlyphLayout(gameFont, String.valueOf(tItem.getDescription()), Color.WHITE, 220, Align.left, true);
            gameFont.draw(batch, description, resolution.x*0.38f, resolution.y*0.49f);
        }
    }

    public void showMessage(SpriteBatch batch) {
        speechBubble.get_sprite().draw(batch);

        GlyphLayout textMessage = new GlyphLayout(gameFont, String.valueOf(this.message), Color.BLACK, 250, Align.left, true);
        gameFont.draw(batch, textMessage, resolution.x*0.67f, resolution.y*0.85f);
    }

    public boolean buyItem() {
        Item tItem = this.itemsSelected.get(itemSelected-1);
        int itemId = tItem.getId();
        boolean completed = false;
        boolean bought = false;

        if (tItem.getItemType() == 1) {
            for(Attack characterItem : this.character.getAttacks()){
                if (characterItem.getId() == itemId) {
                    bought = true;
                }
            }
            for(Attack characterAttack : proxyDataManager.getAttacksList(this.character.getCharacterClass().getName(), this.character.getLevel())) {
                if (characterAttack.getId() == itemId && !bought) {
                    this.character.addNewAttack(characterAttack);
                }
            }

        } else if (tItem.getItemType() == 2) {
            for(Skill characterItem : this.character.getSkills()){
                if (characterItem.getId() == itemId){
                    bought = true;
                }
            }
            for(Skill characterSkill : proxyDataManager.getSkillsList(this.character.getCharacterClass().getName(), this.character.getLevel())) {
                if (characterSkill.getId() == itemId && !bought) {
                    this.character.addNewSkill(characterSkill);
                }
            }
        } else if (tItem.getItemType() == 3) {
            boolean found = false;
            Item itemFound = null;
            for(Item characterItem : this.character.getItems()){
                if (characterItem.getId() != itemId){
                    for(Item charracterCurrentItem : proxyDataManager.getConsumableItemsList(this.character.getCharacterClass().getName())) {
                        if (charracterCurrentItem.getId() == itemId) {
                            found = true;
                            itemFound = charracterCurrentItem;
                        }
                    }
                    for(Item charracterCurrentItem : proxyDataManager.getWearableItemsList()) {
                        if (charracterCurrentItem.getId() == itemId) {
                            found = true;
                            itemFound = charracterCurrentItem;
                        }
                    }

                } else {
                    bought = true;
                }
            }
            if (found && !bought) {
                this.character.addNewItem(itemFound);
            }
        }
        if (!bought) {
            this.character.setCoins(this.character.getCoins() - tItem.getPrice());
            completed = true;
        } else {
            completed = false;
        }

        return completed;
    }

    public boolean sellItem() {
        boolean completed = false;
        if (this.itemsSelected.size >= 1) {
            Item tItem = this.itemsSelected.get(itemSelected-1);
            int itemId = tItem.getId();
            boolean found = false;
            int foundID = 0;

            if (tItem.getItemType() == 1) {
                for(Attack characterItem : this.character.getAttacks()){
                    if (characterItem.getId() == itemId) {
                        found = true;
                        foundID = itemId;
                    }
                }
                if(found) {
                    this.character.deleteAttack(foundID);
                }
            } else if (tItem.getItemType() == 2) {
                for(Skill characterItem : this.character.getSkills()){
                    if (characterItem.getId() == itemId) {
                        found = true;
                        foundID = itemId;
                    }
                }
                if(found) {
                    this.character.deleteSkill(foundID);
                }
            } else if (tItem.getItemType() == 3) {
                for(Item characterItem : this.character.getItems()){
                    if (characterItem.getId() == itemId) {
                        found = true;
                        foundID = itemId;
                    }
                }
                if(found) {
                    this.character.deleteItem(foundID);
                }
            }

            if (found) {
                this.character.setCoins(this.character.getCoins() + tItem.getPrice());
                this.processConfiguration();
                completed = true;
            } else {
                completed = false;
            }
        } else {
            System.out.println("ItemsSelected vacio");
        }
        return completed;
    }

    public void changeCoinsColor() {
        if (this.actionType == 1 && this.itemsSelected.size >= 1) {
            if (checkPurchase()) {
                this.characterCoinsFont.setColor(Color.WHITE);
            } else {
                this.characterCoinsFont.setColor(Color.RED);
            }
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
                this.message = "Esto ya lo tienes, revisa tu inventario";
            }
        } else if (this.actionType == 2) {
            if (done) {
                this.message = "Venta realizada con éxito";
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
            if (this.itemsType == 0) {
                this.sourceAttaks = proxyDataManager.getAttacksList(this.character.getCharacterClass().getName(), this.character.getLevel());
                for(Attack tItem : sourceAttaks){
                    Item newItem = new Item(tItem.getId(),tItem.getName(), tItem.getDescription(), tItem.getPrice(), tItem.getUnlockLevel() ,tItem.getImagePath(), tItem.getItemType(), tItem.getHPEffect());
                    this.itemsSelected.add(newItem);
                }
            } else if (this.itemsType == 1) {
                this.sourceSkills = proxyDataManager.getSkillsList(this.character.getCharacterClass().getName(), this.character.getLevel());
                for(Skill tItem : sourceSkills){
                    Item newItem = new Item(tItem.getId(),tItem.getName(), tItem.getDescription(), tItem.getPrice(),tItem.getUnlockLevel() ,tItem.getImagePath(), tItem.getItemType(), tItem.getStrengthEffect(), tItem.getSpeedEffect(), tItem.getMagicEffect(), tItem.getResistanceEffect(), tItem.getLuckEffect(), tItem.getmPEffect(), tItem.gethPEffect());
                    this.itemsSelected.add(newItem);
                }
            } else if (this.itemsType == 2) {
                this.sourceItems = proxyDataManager.getWearableItemsList(this.character.getCharacterClass().getName(), this.character.getLevel());
                for(Item tItem : sourceItems){
                    this.itemsSelected.add(tItem);
                }
                this.sourceItems = proxyDataManager.getConsumableItemsList(this.character.getCharacterClass().getName(), this.character.getLevel());
                for(Item tItem : sourceItems){
                    this.itemsSelected.add(tItem);
                }
            }
            this.createItems(this.itemsSelected);
        } else if (this.actionType == 2) {
            for(Attack tItem : this.character.getAttacks()){
                if (tItem.getItemType() == 1) {
                    Item newItem = new Item(tItem.getId(),tItem.getName(), tItem.getDescription(), tItem.getPrice(), tItem.getUnlockLevel() ,tItem.getImagePath(), tItem.getItemType(), tItem.getHPEffect());
                    this.itemsSelected.add(newItem);
                }
            }
            for(Skill tItem : this.character.getSkills()){
                if (tItem.getItemType() == 2) {
                    Item newItem = new Item(tItem.getId(),tItem.getName(), tItem.getDescription(), tItem.getPrice(),tItem.getUnlockLevel() ,tItem.getImagePath(), tItem.getItemType(), tItem.getStrengthEffect(), tItem.getSpeedEffect(), tItem.getMagicEffect(), tItem.getResistanceEffect(), tItem.getLuckEffect(), tItem.getmPEffect(), tItem.gethPEffect());
                    this.itemsSelected.add(newItem);
                }
            }
            for(Item tItem : this.character.getItems()){
                if (tItem.getItemType() == 3) {
                    this.itemsSelected.add(tItem);
                }
            }
            this.createItems(this.itemsSelected);
        }
    }

    private void createItems(Array<Item> currentItems) {
        this.screenItems = new Array<ScreenItem>();
        double lastValueX = 0.9;
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
            lastValueX -= 0.055;
            lastItem++;
            if (lastItem >= 4) {
                lastValueX = 0.9;
                lastValueY += 0.12;
                lastItem = 0;
            }
        }
    }
}
