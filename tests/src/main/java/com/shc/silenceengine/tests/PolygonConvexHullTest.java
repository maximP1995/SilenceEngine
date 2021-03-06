/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2017 Sri Harsha Chilakapati
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.shc.silenceengine.tests;

import com.shc.silenceengine.collision.CollisionTag;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.SceneRenderSystem;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.graphics.cameras.OrthoCam;
import com.shc.silenceengine.graphics.opengl.GLContext;
import com.shc.silenceengine.graphics.opengl.Texture;
import com.shc.silenceengine.input.Keyboard;
import com.shc.silenceengine.io.FilePath;
import com.shc.silenceengine.math.geom2d.Polygon;
import com.shc.silenceengine.scene.Entity;
import com.shc.silenceengine.scene.Scene;
import com.shc.silenceengine.scene.components.CollisionComponent2D;
import com.shc.silenceengine.scene.components.PolygonRenderComponent;
import com.shc.silenceengine.scene.components.SpriteComponent;
import com.shc.silenceengine.utils.TaskManager;

/**
 * @author Sri Harsha Chilakapati
 */
public class PolygonConvexHullTest extends SilenceTest
{
    private Scene    scene;
    private OrthoCam camera;

    @Override
    public void init()
    {
        scene = new Scene();
        camera = new OrthoCam().initProjection(SilenceEngine.display.getWidth(), SilenceEngine.display.getHeight());

        SilenceEngine.io.getImageReader().readImage(FilePath.getResourceFile("test_resources/tree2-final.png"), img ->
        {
            Polygon polygon = Polygon.createConvexHull(img);
            TaskManager.runOnUpdate(() ->
                    scene.addEntity(new PolygonEntity(new Sprite(Texture.fromImage(img)), polygon)));
            img.dispose();
        });

        scene.registerRenderSystem(new SceneRenderSystem());
    }

    @Override
    public void update(float deltaTime)
    {
        if (Keyboard.isKeyTapped(Keyboard.KEY_ESCAPE))
            SilenceEngine.display.close();

        scene.update(deltaTime);
    }

    @Override
    public void render(float delta)
    {
        camera.apply();
        scene.render(delta);
    }

    @Override
    public void resized()
    {
        camera.initProjection(SilenceEngine.display.getWidth(), SilenceEngine.display.getHeight());
        GLContext.viewport(0, 0, SilenceEngine.display.getWidth(), SilenceEngine.display.getHeight());
    }

    private static class PolygonEntity extends Entity
    {
        PolygonEntity(Sprite sprite, Polygon polygon)
        {
            transformComponent.setPosition(250, 250);
            addComponent(new SpriteComponent(sprite));
            addComponent(new CollisionComponent2D(new CollisionTag(), polygon));
            addComponent(new PolygonRenderComponent(polygon));
        }
    }
}
