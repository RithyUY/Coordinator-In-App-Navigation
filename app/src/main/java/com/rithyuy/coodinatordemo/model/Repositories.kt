package com.rithyuy.coodinatordemo.model

import javax.inject.Inject
import javax.inject.Singleton

/****
 *
 * Created by UY RITHY on 12/18/18.
 *
 */

@Singleton
class Repositories @Inject constructor() {

    val teams = arrayListOf(
            Team("Janny", "http://www.talentosaproductions.com/wp-content/uploads/2016/03/Job_2719-web-e1533944947699.jpg"),
            Team("Jacky", "http://static.materialicious.com/images/colorful-and-moody-female-portrait-photography-by-elliott-eng-o.jpg"),
            Team("Proseth", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQhYk0K167LND6TOclSRso-N5adXHrMkB_1gNTurAQX_R2RvFeH"),
            Team("Soksan", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4J3ElMwdh3KzHMLV2TJVUV35MxnyTBaL1Ul2GvCaLTg2_VVKI7w"),
            Team("Srey Kmoa", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRgKZPJJpf2iPGmEpdWMOF3G7SiSSyTDLGF8s_toazmvYXq4VDXg"),
            Team("Nanny", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4T6AXecVPgA75wIpwSUGV9ASQ38IwYC1W4B9vbs6oK-vpcDuukw"),
            Team("Narry", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnsWQpN5T2_JIxX4Ys3W3YeqQMmYM4u0U-gi0q1yz_4-XwrfAp"),
            Team("leap", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShdII4W6H20CERpANlSkTTpy2PXgUrpNCC9EXAhSoVZ14LZEzG"),
            Team("Srey Oun", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqFI9izDcBAwQjZYYpK6zkUHDuwMKdTqkHXalTvs1fL-4bTGNbVg"),
            Team("Dawn", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq45YbuOlvSdDXfb-kEys6reK4u3tzRyQ5SeApgbrqwBi8Afrc2A"),
            Team("Prosak", "https://farm1.staticflickr.com/475/18784149924_a2132a1c25_b.jpg"),
            Team("Sreng", "https://static1.squarespace.com/static/560d1e4ce4b02c7b93fb09fc/59db5ca2edaed846531da1fe/59dc9a89c534a5f236447cd7/1507730865593/Dreaming++Stavanger+Portrait+photography+portrett+fotograf.jpg")
    )
}