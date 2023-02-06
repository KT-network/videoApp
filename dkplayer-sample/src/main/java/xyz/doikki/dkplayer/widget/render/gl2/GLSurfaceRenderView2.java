package xyz.doikki.dkplayer.widget.render.gl2;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import xyz.doikki.dkplayer.widget.render.gl2.chooser.GLConfigChooser;
import xyz.doikki.dkplayer.widget.render.gl2.contextfactory.GLContextFactory;
import xyz.doikki.dkplayer.widget.render.gl2.filter.GlFilter;
import xyz.doikki.videoplayer.player.AbstractPlayer;
import xyz.doikki.videoplayer.render.IRenderView;
import xyz.doikki.videoplayer.render.MeasureHelper;

public class GLSurfaceRenderView2 extends GLSurfaceView implements IRenderView {

    private final GLVideoRenderer renderer;

    public GLSurfaceRenderView2(Context context) {
        this(context, null);
    }

    public GLSurfaceRenderView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextFactory(new GLContextFactory());
        setEGLConfigChooser(new GLConfigChooser());
        renderer = new GLVideoRenderer(this);
        setRenderer(renderer);
    }

    private final MeasureHelper mMeasureHelper = new MeasureHelper();

    @Override
    public void attachToPlayer(@NonNull AbstractPlayer player) {
        this.renderer.setPlayer(player);
    }

    @Override
    public void setVideoSize(int videoWidth, int videoHeight) {
        if (videoWidth > 0 && videoHeight > 0) {
            mMeasureHelper.setVideoSize(videoWidth, videoHeight);
            requestLayout();
        }
    }

    @Override
    public void setVideoRotation(int degree) {
        mMeasureHelper.setVideoRotation(degree);
        setRotation(degree);
    }

    @Override
    public void setScaleType(int scaleType) {
        mMeasureHelper.setScreenScale(scaleType);
        requestLayout();
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public Bitmap doScreenShot() {
        return null;
    }

    @Override
    public void release() {

    }

    public void setGlFilter(GlFilter glFilter) {
        renderer.setGlFilter(glFilter);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int[] measuredSize = mMeasureHelper.doMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measuredSize[0], measuredSize[1]);
    }

    @Override
    public void onPause() {
        super.onPause();
        renderer.release();
    }
}
