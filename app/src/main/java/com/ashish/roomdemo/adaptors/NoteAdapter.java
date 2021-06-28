package com.ashish.roomdemo.adaptors;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashish.roomdemo.R;
import com.ashish.roomdemo.activities.EditActivity;
import com.ashish.roomdemo.constants.Constants;
import com.ashish.roomdemo.database.AppDatabase;
import com.ashish.roomdemo.model.NoteModel;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    private Context context;
    private List<NoteModel> mNoteModelList;

    public NoteAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(mNoteModelList.get(i).getName());
        myViewHolder.note.setText(mNoteModelList.get(i).getEmail());

    }

    @Override
    public int getItemCount() {
        if (mNoteModelList == null) {
            return 0;
        }
        return mNoteModelList.size();

    }

    public void setTasks(List<NoteModel> noteModelList) {
        mNoteModelList = noteModelList;
        notifyDataSetChanged();
    }

    public List<NoteModel> getTasks() {

        return mNoteModelList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, note;
        ImageView editImage;
        AppDatabase mDb;

        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            mDb = AppDatabase.getInstance(context);
            name = itemView.findViewById(R.id.person_name);
            note = itemView.findViewById(R.id.person_email);

            editImage = itemView.findViewById(R.id.edit_Image);
            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int elementId = mNoteModelList.get(getAdapterPosition()).getId();
                    Intent i = new Intent(context, EditActivity.class);
                    i.putExtra(Constants.UPDATE_Note_Id, elementId);
                    context.startActivity(i);
                }
            });
        }
    }
}
