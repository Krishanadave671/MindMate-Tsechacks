package com.example.tsechacksapp.data

data class ImgVidData(
    var Details:details? = details("0000","0000","Self"),
    var Image: MutableList<img>? = mutableListOf() ,
    var Video: MutableList<vid>? = mutableListOf()
){

}

data class img(
    var ImageDesc: String? ="",
    var ImgName: String?=""
)

data class vid(
    var VideoDesc: String? ="",
    var VidName: String?=""
)

data class details (
    var DOB: String?="0000",
    var AD: String?="0000",
    var Relation: String?="Self"
)
