package com.example.mid_exam_lenaalhajsalem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView artworkCounterTextView;
    private ImageView artworkImageView;
    private TextView artworkTitleTextView;
    private TextView artworkArtistYearTextView;
    private Button previousArtworkButton;
    private Button nextArtworkButton;

    private int currentIndexArtwork = 0;

    // Data for the 6 artworks (5 updated classic paintings + Mona Lisa)
    private final int[] artworkImages = {
            R.drawable.art_1,
            R.drawable.art_2,
            R.drawable.art_3,
            R.drawable.art_4,
            R.drawable.art_5,
            R.drawable.art_6
    };

    private final String[] artworkTitles = {
            "The Starry Night",
            "The Scream",
            "Spring Flowers",
            "The Persistence of Memory",
            "Girl with a Pearl Earring",
            "Mona Lisa"
    };

    private final String[] artworkArtists = {
            "Vincent van Gogh",
            "Edvard Munch",
            "Botanical Art",
            "Salvador Dali",
            "Johannes Vermeer",
            "Leonardo da Vinci"
    };

    private final String[] artworkYears = {
            "1889",
            "1893",
            "2021",
            "1931",
            "1665",
            "1503"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artworkCounterTextView = findViewById(R.id.tvArtworkCounter);
        artworkImageView = findViewById(R.id.ivArtworkImage);
        artworkTitleTextView = findViewById(R.id.tvArtworkTitle);
        artworkArtistYearTextView = findViewById(R.id.tvArtworkArtistYear);
        previousArtworkButton = findViewById(R.id.btnPreviousArtwork);
        nextArtworkButton = findViewById(R.id.btnNextArtwork);

        // Restore state if available
        if (savedInstanceState != null) {
            currentIndexArtwork = savedInstanceState.getInt("saved_index", 0);
        }

        updateArtworkDisplay();

        previousArtworkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndexArtwork > 0) {
                    currentIndexArtwork--;
                    updateArtworkDisplay();
                }
            }
        });

        nextArtworkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndexArtwork < artworkImages.length - 1) {
                    currentIndexArtwork++;
                    updateArtworkDisplay();
                }
            }
        });

        // Explicit Intent when clicking artwork image
        artworkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);
                detailIntent.putExtra("artwork_title", artworkTitles[currentIndexArtwork]);
                detailIntent.putExtra("artwork_artist", artworkArtists[currentIndexArtwork]);
                detailIntent.putExtra("artwork_year", artworkYears[currentIndexArtwork]);
                startActivity(detailIntent);
            }
        });
    }

    private void updateArtworkDisplay() {
        // Set image and text
        artworkImageView.setImageResource(artworkImages[currentIndexArtwork]);
        artworkTitleTextView.setText(artworkTitles[currentIndexArtwork]);
        artworkArtistYearTextView.setText(artworkArtists[currentIndexArtwork] + " (" + artworkYears[currentIndexArtwork] + ")");

        // Counter text (e.g., 1 / 6)
        int position = currentIndexArtwork + 1;
        artworkCounterTextView.setText(position + " / " + artworkImages.length);

        // Button enable/disable logic
        if (currentIndexArtwork == 0) {
            previousArtworkButton.setEnabled(false);
        } else {
            previousArtworkButton.setEnabled(true);
        }

        if (currentIndexArtwork == artworkImages.length - 1) {
            nextArtworkButton.setEnabled(false);
        } else {
            nextArtworkButton.setEnabled(true);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("saved_index", currentIndexArtwork);
    }
}
