package com.rithyuy.coodinatordemo.src.createproject.teammember

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rithyuy.coodinatordemo.R
import com.rithyuy.coodinatordemo.adapter.RecyclerViewAdapter
import com.rithyuy.coodinatordemo.base.BaseView
import com.rithyuy.coodinatordemo.base.ViewHolderProvider
import com.rithyuy.coodinatordemo.model.SelectableTeam
import com.rithyuy.coodinatordemo.model.Team
import com.rithyuy.coodinatordemo.src.createproject.FragmentHostNavigator
import kotlinx.android.synthetic.main.fragment_team_member.view.*

class TeamMemberFragment : Fragment(), BaseView, ViewHolderProvider<TeamViewHolder> {

    private val teams = arrayListOf<Any>(
            SelectableTeam(Team("Parker", ""), false),
            SelectableTeam(Team("Rithy", ""), true),
            SelectableTeam(Team("Vector", ""), true)
    )
    private val adapter by lazy { RecyclerViewAdapter(teams, this) }
    private lateinit var hostNavigator: FragmentHostNavigator

    fun bindHost(hostNavigator: FragmentHostNavigator): TeamMemberFragment {
        this.hostNavigator = hostNavigator
        return this
    }

    override val layoutResource: Int get() = R.layout.fragment_team_member

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResource, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rvMember.layoutManager = LinearLayoutManager(context)
        view.rvMember.adapter = adapter
        view.btnSkip.setOnClickListener { hostNavigator.moveNextOf(this) }
        view.btnAdd.setOnClickListener { animateShowList(view) }
        adapter.onItemClickListener.subscribe(this::onItemClicked)
    }

    private fun animateShowList(view: View) {
        
    }

    private fun onItemClicked(position: Int) {
        val item =(teams[position] as SelectableTeam)
        item.isSelect = !item.isSelect
        adapter.notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(layoutInflater.inflate(R.layout.view_holder_team, parent, false))
    }
}