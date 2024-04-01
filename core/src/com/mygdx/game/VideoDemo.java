package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;

import java.io.FileNotFoundException;

public class VideoDemo extends ApplicationAdapter {
	SpriteBatch batch;
	private VideoPlayer videoPlayer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		videoPlayer = VideoPlayerCreator.createVideoPlayer();
		videoPlayer.isPlaying();
		
		videoPlayer.setOnCompletionListener(new VideoPlayer.CompletionListener() {
			@Override
			public void onCompletionListener(FileHandle file) {
				// Do something
			}
		});

		try {
			videoPlayer.play(Gdx.files.internal("start/outro.webm"));
		} catch (FileNotFoundException e) {
			Gdx.app.error("gdx-video", "Oh no!");
		}
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		videoPlayer.update();

		batch.begin();

		Texture frame = videoPlayer.getTexture();
		if (frame != null)
			batch.draw(frame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
