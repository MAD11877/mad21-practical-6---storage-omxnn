package sg.edu.np.prac3githhub;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ProfilesViewHolder extends RecyclerView.ViewHolder {
    TextView txt1;
    TextView txt2;
    ImageView image;
    ImageView image2;

    public ProfilesViewHolder(View itemView){
        super(itemView);
        txt1 = itemView.findViewById(R.id.textView3);
        txt2 = itemView.findViewById(R.id.textView4);
        image = itemView.findViewById(R.id.img_profile);
        image2 = itemView.findViewById(R.id.imageView3);
    }
}