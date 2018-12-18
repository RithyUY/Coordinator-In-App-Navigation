package com.rithyuy.coodinatordemo.model

data class Team(var name: String, var imageUrl: String)
data class SelectableTeam(val team: Team, var isSelect: Boolean = false)