package ru.memebattle.feature

import android.os.Bundle
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import client.common.feature.memechill.MemeChillState
import client.common.feature.memechill.MemeChillViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_meme_chill.*
import kotlinx.android.synthetic.main.fragment_memebattle.*
import kotlinx.android.synthetic.main.fragment_memebattle.image1
import kotlinx.android.synthetic.main.fragment_memebattle.image2
import kotlinx.android.synthetic.main.fragment_memebattle.like1
import kotlinx.android.synthetic.main.fragment_memebattle.like2
import kotlinx.android.synthetic.main.fragment_memebattle.save_first_meme_btn
import kotlinx.android.synthetic.main.fragment_memebattle.save_second_meme_btn
import kotlinx.android.synthetic.main.fragment_memebattle.share_first_meme_btn
import kotlinx.android.synthetic.main.fragment_memebattle.share_second_meme_btn
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import ru.memebattle.R
import ru.memebattle.common.GameMode
import ru.memebattle.common.dto.game.MemeModel
import ru.memebattle.core.utils.log
import ru.memebattle.core.utils.saveImage
import ru.memebattle.core.utils.shareImage
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class MemeChillFragment : Fragment(R.layout.fragment_meme_chill) {

    private var isButtonDisabled = true
    private val viewModel: MemeChillViewModel by viewModel()
    private val onSaveClickListener: (v: View) -> Unit = {
        when (it.id) {
            R.id.save_first_meme_btn -> {
                saveImage(image1.drawable.toBitmap())
            }
            R.id.save_second_meme_btn -> {
                saveImage(image2.drawable.toBitmap())
            }
        }
    }
    private val onShareClickListener: (v: View) -> Unit = {
        when (it.id) {
            R.id.share_first_meme_btn -> {
                shareImage(image1.drawable.toBitmap())
            }
            R.id.share_second_meme_btn -> {
                shareImage(image2.drawable.toBitmap())
            }
        }
    }

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mode = arguments?.getSerializable("GameMode") as? GameMode ?: GameMode.CLASSIC
        viewModel.setGameMode(mode)
        viewModel.state.platform.observe(viewLifecycleOwner) { state ->
            when (state) {
                MemeChillState.Loading -> {
                    log("loading")
                }
                is MemeChillState.Error -> {
                    log(state.error.toString())
                }
                is MemeChillState.SuccessMemePair -> {
                    onNextMemesPair(state.memes)
                }
            }
        }

        image1.setOnClickListener {
            result_view1.isVisible = true
            result_view2.isVisible = true
            like1.isVisible = true
            lifecycleScope.launch {
                delay(RESULT_DELAY)
                viewModel.getMemesPair()
            }
        }

        image2.setOnClickListener {
            result_view1.isVisible = true
            result_view2.isVisible = true
            like2.isVisible = true
            lifecycleScope.launch {
                delay(RESULT_DELAY)
                viewModel.getMemesPair()
            }
        }
        save_first_meme_btn.setOnClickListener(onSaveClickListener)
        save_second_meme_btn.setOnClickListener(onSaveClickListener)
        share_first_meme_btn.setOnClickListener(onShareClickListener)
        share_second_meme_btn.setOnClickListener(onShareClickListener)
        viewModel.getMemesPair()
    }

    private fun onNextMemesPair(memesPair: Pair<MemeModel, MemeModel>) {
        isButtonDisabled = false
        result_view1.isVisible = false
        result_view2.isVisible = false
        like1.isVisible = false
        like2.isVisible = false
        save_first_meme_btn.isVisible = true
        save_second_meme_btn.isVisible = true
        share_first_meme_btn.isVisible = true
        share_second_meme_btn.isVisible = true
        Glide.with(requireActivity())
            .load(memesPair.first.url)
            .placeholder(resources.getDrawable(R.drawable.wait_image))
            .into(image1)
        Glide.with(requireActivity())
            .load(memesPair.second.url)
            .placeholder(resources.getDrawable(R.drawable.wait_image))
            .into(image2)
    }

    companion object {
        private const val RESULT_DELAY = 500L
    }
}