package com.example.demohrutvik

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.demohrutvik.databinding.ItemaddBinding


// Step 1: Create the Adapter class
class StudentAdapter :  RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {


    private var callList = emptyList<InstructorStudentList>()
    class MyViewHolder(private val binding : ItemaddBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: InstructorStudentList,index: Int){
            binding.variable = currentItem
            binding.tvName.text = currentItem.StudentName.toString()
            binding.tvEvaluation.text=currentItem.ProgramName.toString()
         //   val formattedTotalPayableInFloat = String.format("%.2f", )


            binding.tvDue.text = "Due: $"+currentItem.DueAmount
            binding.tvIShare.text="I-Share: "+currentItem.InstructorShare
            binding.btnPrivate.text=currentItem.CourseCode

            binding.tvNumber.text = (index + 1).toString()
            Log.d("number", (index+1).toString())
            Log.d("number2", binding.tvNumber.text.toString())
            binding.btnCall.setOnClickListener {

                // Get the phone number from the currentItem
                val phoneNumber = currentItem.Phone.trim()

                // Create an intent with the ACTION_DIAL action
                val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }

                // Start the dialer activity
                itemView.context.startActivity(dialIntent)

            }
            binding.btnMessage.setOnClickListener {
                val phoneNumber = currentItem.Phone.trim()

                // Create an intent with the ACTION_VIEW action for SMS
                val messageIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("sms:$phoneNumber")
                }

                // Start the messaging activity
                itemView.context.startActivity(messageIntent)
            }
            binding.btnAddress.setOnClickListener {
                val address = currentItem.StudentAddress ?: run {
                    Toast.makeText(binding.root.context, "Address is not available", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val encodedAddress = Uri.encode(address.trim())
                val locationUri = "geo:0,0?q=$encodedAddress"
                val mapIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(locationUri)
                }

                val packageManager = binding.root.context.packageManager
                val resolveInfo = mapIntent.resolveActivity(packageManager)

                if (resolveInfo != null) {
                    // An application can handle the geo URI, start the intent
                    binding.root.context.startActivity(mapIntent)
                } else {
                    // Fallback to web-based map URL
                    val webLocationUri = "https://www.google.com/maps/search/?api=1&query=$encodedAddress"
                    val webMapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webLocationUri))

                    if (webMapIntent.resolveActivity(packageManager) != null) {
                        // Ensure that the intent resolves to a web browser
                        binding.root.context.startActivity(webMapIntent)
                    } else {
                        // No application or browser available to handle the request
                        Toast.makeText(binding.root.context, "No application found to handle map request", Toast.LENGTH_SHORT).show()
                        Log.e("MapIntent", "No application found to handle map request.")
                    }
                }
            }




        }
        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemaddBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }


    override fun getItemCount(): Int {
        return callList.size
    }



    fun setData(Result:List<InstructorStudentList>){
//        this.callList= user
//        notifyDataSetChanged()

        val userDiffUtil = DiffUtilExt(callList, Result)
        val userDiffUtilResult = DiffUtil.calculateDiff(userDiffUtil)
        callList = Result
        userDiffUtilResult.dispatchUpdatesTo(this)

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = callList.getOrNull(position)

        currentItem?.let {
            holder.bind(it,position)
        }
    }

}
