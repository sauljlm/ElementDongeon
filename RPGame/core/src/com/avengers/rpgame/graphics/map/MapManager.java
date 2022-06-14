package com.avengers.rpgame.graphics.map;

import com.avengers.rpgame.RPGame;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import static com.avengers.rpgame.utils.Resources.resourceOverworldMap;

public class MapManager {
    private RPGame game;
    private String mapFileLocation;
    private OrthographicCamera camera;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    public MapManager(String mapFileLocation, OrthographicCamera camera, RPGame game) {
        this.game = game;
        this.mapFileLocation = mapFileLocation;
        this.camera = camera;
        //The following lines need to be here and not on the render method for perfomance reasons
        //mapLoader and renderer memory allocations should not be done on render time, but on the constructor of the Screen
        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load(mapFileLocation);
        this.mapRenderer = new OrthogonalTiledMapRenderer(map);
        this.mapRenderer.setView(this.camera);
    }

    public RPGame getGame() {
        return game;
    }

    public void setGame(RPGame game) {
        this.game = game;
    }

    public String getMapFileLocation() {
        return mapFileLocation;
    }

    public void setMapFileLocation(String mapFileLocation) {
        this.mapFileLocation = mapFileLocation;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public TmxMapLoader getMapLoader() {
        return mapLoader;
    }

    public void setMapLoader(TmxMapLoader mapLoader) {
        this.mapLoader = mapLoader;
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public OrthogonalTiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }

    public void setMapRenderer(OrthogonalTiledMapRenderer mapRenderer) {
        this.mapRenderer = mapRenderer;
    }

    public void render(){
        mapRenderer.setView(camera);
        mapRenderer.render();
    }
    public void dispose(){
        mapRenderer.dispose();
        game.dispose();
        map.dispose();
    }
}
