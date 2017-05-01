package au.com.gridstone.arcmotionbugdemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.transition.ArcMotion;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DemoActivity extends AppCompatActivity {

  private static final float END_POINT_MARGIN = 10;
  private ArcMotion arcMotion = new ArcMotion();

  private float fabDefaultX;
  private float fabDefaultY;
  private float endX;
  private float endY;

  @BindView(R.id.container)
  View container;

  @BindView(R.id.coordinates)
  TextView coordinatesView;

  @BindView(R.id.fab)
  FloatingActionButton fab;

  @OnClick(R.id.button_play_animation)
  void onPlayAnimationClicked() {
    ObjectAnimator.ofFloat(fab, View.X.getName(), View.Y.getName(), arcMotion.getPath(fabDefaultX, fabDefaultY, endX, endY)).start();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo);
    ButterKnife.bind(this);

    container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        fabDefaultX = fab.getX();
        fabDefaultY = fab.getY();
        // startX and startY should always be greater than 10.
        endY = END_POINT_MARGIN;
        float deltaX = -Math.min((fabDefaultY - endY) / 2, fabDefaultX - END_POINT_MARGIN);
        endX = fabDefaultX + deltaX;
        coordinatesView.setText(String.format(Locale.getDefault(), "Start (x,y) = (%f, %f)\nEnd (x,y) = (%f, %f)\nVertical distance = %f\nHorizontal distance = %f",
            fabDefaultX, fabDefaultY, endX, endY, Math.abs(endY - fabDefaultY), Math.abs(endX - fabDefaultX)));
      }
    });
  }
}
