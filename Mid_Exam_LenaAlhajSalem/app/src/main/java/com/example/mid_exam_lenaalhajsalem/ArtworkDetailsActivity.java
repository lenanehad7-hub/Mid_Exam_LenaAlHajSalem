package com.example.mid_exam_lenaalhajsalem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ArtworkDetailsActivity extends AppCompatActivity {

    private TextView detailTitleTextView;
    private TextView detailArtistYearTextView;
    private Button visitWebsiteButton;
    private Button backToGalleryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_details);

        detailTitleTextView = findViewById(R.id.tvDetailTitle);
        detailArtistYearTextView = findViewById(R.id.tvDetailArtistYear);
        visitWebsiteButton = findViewById(R.id.btnVisitWebsite);
        backToGalleryButton = findViewById(R.id.btnBackToGallery);

        // Get data from intent
        Intent incomingIntent = getIntent();
        if (incomingIntent != null) {
            String artworkTitle = incomingIntent.getStringExtra("artwork_title");
            String artworkArtist = incomingIntent.getStringExtra("artwork_artist");
            String artworkYear = incomingIntent.getStringExtra("artwork_year");

            if (artworkTitle != null) {
                detailTitleTextView.setText(artworkTitle);
            }
            if (artworkArtist != null && artworkYear != null) {
                detailArtistYearTextView.setText(artworkArtist + " (" + artworkYear + ")");
            }
        }

        // Implicit Intent to visit website
        visitWebsiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpageUri = Uri.parse("https://artsandculture.google.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpageUri);
                startActivity(webIntent);
            }
        });

        // Back to gallery button
        backToGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
