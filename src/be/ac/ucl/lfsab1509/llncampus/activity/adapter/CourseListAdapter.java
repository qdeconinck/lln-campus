package be.ac.ucl.lfsab1509.llncampus.activity.adapter;

import java.util.ArrayList;

import be.ac.ucl.lfsab1509.llncampus.Course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * LLNCampus. A application for students at the UCL (Belgium).
    Copyright (C) 2013 Benjamin Baugnies, Quentin De Coninck, Ahn Tuan Le Pham and Damien Mercier
    Copyright (C) 2014 Quentin De Coninck

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Class intended to make a list view of Courses.
 */
public class CourseListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
 
    private ArrayList<Course> list;
 
    /**
	 * Constructor.
	 * @param context
	 * 			Context to find the LayoutInflater.
	 * @param list
	 * 			The list of Courses.
	 */
    public CourseListAdapter(Context context, ArrayList<Course> list) {
        mInflater = LayoutInflater.from(context);
        this.list = list;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
    	// Use a default layout.
        if (convertView == null) {
            convertView = mInflater.inflate(android.R.layout.simple_list_item_2, parent, false);
        }
        TextView title = (TextView) convertView.findViewById(android.R.id.text1);
        TextView sub = (TextView) convertView.findViewById(android.R.id.text2);
 
        Course info = list.get(position);
        title.setText(info.getCourseCode());
        sub.setText(info.getCoursName());
        return convertView;
    }
 
    public int getCount() {
        return list.size();
    }
 
    public Object getItem(int arg0) {
        return list.get(arg0);
    }
 
    public long getItemId(int arg0) {
        return arg0;
    }

}
