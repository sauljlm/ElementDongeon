package com.avengers.rpgame.graphics.inventary;

import com.avengers.rpgame.data.dataStorage.ProxyDataManager;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.store.Coin;
import com.avengers.rpgame.graphics.store.ScreenItem;
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

import java.util.ArrayList;

public class Inventary {
    private static Inventary instance;
    private Array<ScreenItem> screenItems = new Array<ScreenItem>();
    private ArrayList<Attack> sourceAttaks = new ArrayList<Attack>();
    private ArrayList<Skill> sourceSkills = new ArrayList<Skill>();
    private ArrayList<Item>sourceItems = new ArrayList<Item>();
    private ProxyDataManager proxyDataManager;
    private AbstractCharacter character;
    private InventaryBG inventary = new InventaryBG(0.64f, 0.85f);
    private BitmapFont gameFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.inventaryTittleFontSize, Color.WHITE, false, Color.BLACK);
    private BitmapFont nameFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.inventaryNameFontSize, Color.WHITE, false, Color.BLACK);
    private GameConfig gameConfig;

    private GameStatus gameStatus;
    private Vector2 resolution;
    private int typeSelected;
    private boolean showInventary;

    private Inventary () {
        this.gameConfig = GameConfig.getInstance();
        this.gameStatus = GameStatus.getInstance();
        this.proxyDataManager = new ProxyDataManager();
        this.resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        this.character = GameStatus.getInstance().getPlayerParty().getActivePartyMember();
        showInventary = false;
        this.updateInventaryItems();
        // this.setShowInventary(false);
    }

    public static void setInstance(Inventary instance) {
        Inventary.instance = instance;
    }
    public static Inventary getInstance() {
        if (instance == null) {
            instance = new Inventary();
        }
        return instance;
    }
    public boolean getShowInventary() { return showInventary; }
    public void setShowInventary(boolean showInventary) {
        this.showInventary = showInventary;
    }

    public void switchInventory(){
        if (showInventary) {
            showInventary= false;
            return;
        }
        if(!showInventary){
            showInventary = true;
        }
    }

    public void draw (SpriteBatch batch) {
        this.updateInventaryItems();
        if (this.getShowInventary()) {
            inventary.get_sprite().draw(batch);
            gameFont.draw(batch, "Inventario", resolution.x*0.465f, resolution.y*0.86f);
            for(ScreenItem item: screenItems) {
                item.get_sprite().draw(batch);
                GlyphLayout description = new GlyphLayout(nameFont, item.getDescription(), Color.WHITE, 90, Align.left, true);
                nameFont.draw(batch, description, item.getItemX(), item.getItemY()-10);
            }
            gameFont.draw(batch, "Ataques", resolution.x*0.38f, resolution.y*0.78f);
            gameFont.draw(batch, "Habilidades", resolution.x*0.38f, resolution.y*0.6f);
            gameFont.draw(batch, "Items", resolution.x*0.38f, resolution.y*0.42f);
        }
    }

    private void updateInventaryItems() {
        sourceAttaks.clear();
        sourceSkills.clear();
        screenItems.clear();
        sourceItems.clear();
        this.character = GameStatus.getInstance().getPlayerParty().getActivePartyMember();
        this.typeSelected = 1;
        if (this.character.getAttacks().size() >= 1)
            for(Attack tItem : this.character.getAttacks()){
                this.sourceAttaks.add(tItem);
            }
        if (this.character.getSkills().size() >= 1)
            for(Skill tItem : this.character.getSkills()){
                this.sourceSkills.add(tItem);
            }
        if (this.character.getItems().size() >= 1)
            for(Item tItem : this.character.getItems()){
                this.sourceItems.add(tItem);
            }
        this.createItems();
    }

    private void updateTypeSelected (int type) {
        this.typeSelected = type;
    }

    private void createItems() {
        this.screenItems = new Array<ScreenItem>();
        double lastValueX = 0.62;
        double lastValueY = 0.35;
        int lastItem = 0;

        for (Attack tItem : this.sourceAttaks) {
            ScreenItem item = new ScreenItem(tItem.getImagePath(), tItem.getPrice(), tItem.getDescription(), lastValueX, lastValueY, 100);
            screenItems.add(item);
            lastValueX -= 0.055;
            lastItem++;
            if (lastItem >= 5) {
                lastValueX = 0.62;
                lastValueY += 0.12;
                lastItem = 0;
            }
        }
        lastValueX = 0.62;
        lastValueY += 0.18;
        lastItem = 0;
        for (Skill tItem : this.sourceSkills) {
            ScreenItem item = new ScreenItem(tItem.getImagePath(), tItem.getPrice(), tItem.getDescription(), lastValueX, lastValueY, 100);
            screenItems.add(item);
            lastValueX -= 0.055;
            lastItem++;
            if (lastItem >= 5) {
                lastValueX = 0.62;
                lastValueY += 0.12;
                lastItem = 0;
            }
        }
        lastValueX = 0.62;
        lastValueY += 0.18;
        lastItem = 0;
        for (Item tItem : this.sourceItems) {
            ScreenItem item = new ScreenItem(tItem.getImagePath(), tItem.getPrice(), tItem.getDescription(), lastValueX, lastValueY, 100);
            screenItems.add(item);
            lastValueX -= 0.055;
            lastItem++;
            if (lastItem >= 5) {
                lastValueX = 0.62;
                lastValueY += 0.12;
                lastItem = 0;
            }
        }
    }
}
