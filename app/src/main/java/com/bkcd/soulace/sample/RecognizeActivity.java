package com.bkcd.soulace.sample;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.microsoft.projectoxford.emotion.EmotionServiceClient;
import com.microsoft.projectoxford.emotion.EmotionServiceRestClient;
import com.microsoft.projectoxford.emotion.contract.FaceRectangle;
import com.microsoft.projectoxford.emotion.contract.RecognizeResult;
import com.microsoft.projectoxford.emotion.rest.EmotionServiceException;
import com.microsoft.projectoxford.face.FaceServiceRestClient;
import com.microsoft.projectoxford.face.contract.Face;
import com.yalantis.contextmenu.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class RecognizeActivity extends AppCompatActivity {

    // Flag to indicate which task is to be performed.
    private static final int REQUEST_SELECT_IMAGE = 0;
    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_SELECT_IMAGE_IN_ALBUM = 1;
    int c=0;

    // The URI of photo taken with camera
    private Uri mUriPhotoTaken;
    String emotionResult;

    // The button to select an image
    private Button mButtonSelectImage,mButtonGallery;

    // The URI of the image selected to detect.
    private Uri mImageUri;
    TextView tv, tv1;
    // The image selected to detect.
    private Bitmap mBitmap;


    // The edit to show status and result.
    private TextView mEditText;
    Button btn;
    private EmotionServiceClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognize);

        if (client == null) {
            client = new EmotionServiceRestClient(getString(R.string.subscription_key));
        }


        tv=(TextView) findViewById(R.id.intro);
        tv1=(TextView) findViewById((R.id.sad_angry));
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/MotionPicture_PersonalUseOnly.ttf");
        tv.setTypeface(custom_font);


        mButtonSelectImage = (Button) findViewById(R.id.buttonSelectImage);
        mButtonGallery=(Button) findViewById(R.id.button_select_a_photo_in_album);
        mEditText = (TextView) findViewById(R.id.editTextResult);
        mEditText.setVisibility(View.GONE);

    }



    public void doRecognize() {
        mButtonSelectImage.setEnabled(false);

        // Do emotion detection using auto-detected faces.
        try {
            new doRequest(false).execute();
        } catch (Exception e) {
            mEditText.append("Error encountered. Exception is: " + e.toString());
        }

        String faceSubscriptionKey = getString(R.string.faceSubscription_key);
        if (faceSubscriptionKey.equalsIgnoreCase("Please_add_the_face_subscription_key_here")) {
            mEditText.append("\n\nThere is no face subscription key in res/values/strings.xml. Skip the sample for detecting emotions using face rectangles\n");
        } else {
            // Do emotion detection using face rectangles provided by Face API.
            try {
                new doRequest(true).execute();
            } catch (Exception e) {
                mEditText.append("Error encountered. Exception is: " + e.toString());
            }
        }
    }


    private List<RecognizeResult> processWithAutoFaceDetection() throws EmotionServiceException, IOException {
        Log.d("emotion", "Start emotion detection with auto-face detection");

        Gson gson = new Gson();

        // Put the image into an input stream for detection.
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(output.toByteArray());

        long startTime = System.currentTimeMillis();
        // -----------------------------------------------------------------------
        // KEY SAMPLE CODE STARTS HERE
        // -----------------------------------------------------------------------

        List<RecognizeResult> result = null;
        //
        // Detect emotion by auto-detecting faces in the image.
        //
        result = this.client.recognizeImage(inputStream);

        String json = gson.toJson(result);
        Log.d("result", json);

        Log.d("emotion", String.format("Detection done. Elapsed time: %d ms", (System.currentTimeMillis() - startTime)));
        // -----------------------------------------------------------------------
        // KEY SAMPLE CODE ENDS HERE
        // -----------------------------------------------------------------------
        return result;
    }

    private List<RecognizeResult> processWithFaceRectangles() throws EmotionServiceException, com.microsoft.projectoxford.face.rest.ClientException, IOException {
        Log.d("emotion", "Do emotion detection with known face rectangles");
        Gson gson = new Gson();

        // Put the image into an input stream for detection.
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(output.toByteArray());

        long timeMark = System.currentTimeMillis();
        Log.d("emotion", "Start face detection using Face API");
        FaceRectangle[] faceRectangles = null;
        String faceSubscriptionKey = getString(R.string.faceSubscription_key);
        FaceServiceRestClient faceClient = new FaceServiceRestClient(faceSubscriptionKey);
        Face faces[] = faceClient.detect(inputStream, false, false, null);
        Log.d("emotion", String.format("Face detection is done. Elapsed time: %d ms", (System.currentTimeMillis() - timeMark)));

        if (faces != null) {
            faceRectangles = new FaceRectangle[faces.length];

            for (int i = 0; i < faceRectangles.length; i++) {
                // Face API and Emotion API have different FaceRectangle definition. Do the conversion.
                com.microsoft.projectoxford.face.contract.FaceRectangle rect = faces[i].faceRectangle;
                faceRectangles[i] = new com.microsoft.projectoxford.emotion.contract.FaceRectangle(rect.left, rect.top, rect.width, rect.height);
            }
        }

        List<RecognizeResult> result = null;
        if (faceRectangles != null) {
            inputStream.reset();

            timeMark = System.currentTimeMillis();
            Log.d("emotion", "Start emotion detection using Emotion API");
            // -----------------------------------------------------------------------
            // KEY SAMPLE CODE STARTS HERE
            // -----------------------------------------------------------------------
            result = this.client.recognizeImage(inputStream, faceRectangles);

            String json = gson.toJson(result);
            Log.d("result", json);
            // -----------------------------------------------------------------------
            // KEY SAMPLE CODE ENDS HERE
            // -----------------------------------------------------------------------
            Log.d("emotion", String.format("Emotion detection is done. Elapsed time: %d ms", (System.currentTimeMillis() - timeMark)));
        }
        return result;
    }

    private class doRequest extends AsyncTask<String, String, List<RecognizeResult>> {
        // Store error message
        private Exception e = null;
        private boolean useFaceRectangles = false;

        public doRequest(boolean useFaceRectangles) {
            this.useFaceRectangles = useFaceRectangles;
        }

        @Override
        protected List<RecognizeResult> doInBackground(String... args) {
            if (this.useFaceRectangles == false) {
                try {
                    return processWithAutoFaceDetection();
                } catch (Exception e) {
                    this.e = e;    // Store error
                }
            } else {
                try {
                    return processWithFaceRectangles();
                } catch (Exception e) {
                    this.e = e;    // Store error
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<RecognizeResult> result) {
            super.onPostExecute(result);
            // Display based on error existence

            if (this.useFaceRectangles == false) {
                mEditText.append("\n\nRecognizing emotions with auto-detected face rectangles...\n");
            } else {
                mEditText.append("\n\nRecognizing emotions with existing face rectangles from Face API...\n");
            }
            if (e != null) {
                mEditText.setText("Error: " + e.getMessage());
                this.e = null;
            } else {
                if (result.size() == 0) {
                    mEditText.append("No emotion detected :(");
                } else {
                    Integer count = 0;
                    c++;
                    // Covert bitmap to a mutable bitmap by copying it
                    Bitmap bitmapCopy = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
                    Canvas faceCanvas = new Canvas(bitmapCopy);
                    faceCanvas.drawBitmap(mBitmap, 0, 0, null);
                    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(5);
                    paint.setColor(Color.RED);
                    double vals[]=new double[7];

                    double max=0;
                    int pos=0;
                    if(c==1) {
                        for (RecognizeResult r : result) {


                            mEditText.append(String.format("\t anger: %1$.3f", vals[0] = r.scores.anger*100)+"%\n");
                            mEditText.append(String.format("\t disgust: %1$.3f", vals[1] = r.scores.disgust*100)+"%\n");
                            mEditText.append(String.format("\t fear: %1$.3f", vals[2] = r.scores.fear*100)+"%\n");
                            mEditText.append(String.format("\t happiness: %1$.3f", vals[3] = r.scores.happiness*100)+"%\n");
                            mEditText.append(String.format("\t neutral: %1$.3f", vals[4] = r.scores.neutral*100)+"%\n");
                            mEditText.append(String.format("\t sadness: %1$.3f", vals[5] = r.scores.sadness*100)+"%\n");
                            mEditText.append(String.format("\t surprise: %1$.3f", vals[6] = r.scores.surprise*100)+"%\n");

                            faceCanvas.drawRect(r.faceRectangle.left,
                                    r.faceRectangle.top,
                                    r.faceRectangle.left + r.faceRectangle.width,
                                    r.faceRectangle.top + r.faceRectangle.height,
                                    paint);
                            for (int i = 0; i < 7; i++)
                                if (vals[i] > max) {
                                    max = vals[i];
                                    pos = i;
                                }
                            count++;
                        }
                        ImageView imageView = (ImageView) findViewById(R.id.selectedImage);
                        imageView.setImageDrawable(new BitmapDrawable(getResources(), mBitmap));

                        if (pos == 0)
                            emotionResult = "Anger";
                        if ((pos == 1))
                            emotionResult = "Disgust";
                        if (pos == 2)
                            emotionResult = "Fear";
                        if (pos == 3)
                            emotionResult = "Happiness";
                        if ((pos == 4))
                            emotionResult = "Neutral";
                        if (pos == 5)
                            emotionResult = "Sadness";
                        if ((pos == 6))
                            emotionResult = "Surprise";

                        Intent i = new Intent(RecognizeActivity.this, MainActivity.class);
                        i.putExtra("Emotion Result", emotionResult);
                        i.putExtra("Emotion Analysis", mEditText.getText().toString());

                            mEditText.setText("");

                        startActivity(i);
                    }
                }
            }
            mButtonSelectImage.setEnabled(true);
            mButtonGallery.setEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        c=0;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("ImageUri", mUriPhotoTaken);
    }

    // Recover the saved state when the activity is recreated.
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mUriPhotoTaken = savedInstanceState.getParcelable("ImageUri");
    }

    // Deal with the result of selection of the photos and faces.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case REQUEST_TAKE_PHOTO:
            case REQUEST_SELECT_IMAGE_IN_ALBUM:
                if (resultCode == RESULT_OK) {
                    Uri imageUri;
                    if (data == null || data.getData() == null) {
                        imageUri = mUriPhotoTaken;
                    } else {
                        imageUri = data.getData();
                    }
                    ImageView img=(ImageView) findViewById(R.id.selectedImage);
                    img.setImageURI(imageUri);
                    mBitmap = ImageHelper.loadSizeLimitedBitmapFromUri(imageUri, getContentResolver());
                    doRecognize();
                }
                break;
            default:
                break;
        }
    }

    // When the button of "Take a Photo with Camera" is pressed.
    public void takePhoto(View view) {
        Log.e("+++","++++1++++++");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            // Save the photo taken to a temporary file.
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                Log.e("+++","++++TRY1++++++");
                File file = File.createTempFile("IMG_", ".jpg", storageDir);
                Log.e("+++","++++TRY2++++++");
                mUriPhotoTaken = Uri.fromFile(file);
                Log.e("+++","++++TRY4++++++");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mUriPhotoTaken);
                Log.e("+++","++++TRY3++++++");
                startActivityForResult(intent, REQUEST_TAKE_PHOTO);
            } catch (IOException e) {
                setInfo(e.getMessage());
            }
        }
    }

    // When the button of "Select a Photo in Album" is pressed.
    public void selectImageInAlbum(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM);
        }
    }



    // Set the information panel on screen.
    private void setInfo(String info) {
        TextView textView = (TextView) findViewById(R.id.info);
        textView.setText(info);
    }
}
