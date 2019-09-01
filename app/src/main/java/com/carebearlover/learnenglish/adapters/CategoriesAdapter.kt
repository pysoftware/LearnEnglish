package com.carebearlover.learnenglish.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.carebearlover.learnenglish.R
import com.carebearlover.learnenglish.data.entities.db_tables.CategoriesEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_category.*
import kotlin.math.log

class CategoriesAdapter(
        private val navController: NavController
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private val mCategories = mutableListOf<CategoriesEntity>()

    fun setData(categoriesList: List<CategoriesEntity>) {
        mCategories.clear()
        mCategories.addAll(categoriesList)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                itemView = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_category,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int = mCategories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mCategories[position])
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("category" to mCategories[position].category.trim().toLowerCase())
            navController.navigate(R.id.action_categoriesFragment_to_listFragment, bundle)
            Log.d("bundle", bundle.getString("category"))
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bind(model: CategoriesEntity) {
            tv_category.text = model.category

        }
    }
}
//Look here - Послушайте.
//What can I do for you? - Чем я могу помочь Вам?
//Keep in touch. - He пропадай (будь на связи).
//Good job! - Молодец!
//It is a good idea. - Это хорошая идея.
//I don't саге. - Мне все равно / наплевать.
//It doesn't matter. - Не имеет значения.
//Look out . - Берегись.
//Be careful. - Будь внимателен.
//Don't worry. - He беспокойся.
//Aahh, where have you been? Ааа, где ж ты пропадал?
//My goodness, long time no speak (see) О боги, столько времени не виделись!
//What's his nickname? Какое у него прозвище?
//My name is... / I am... Меня зовут...
//My friends call me...Друзья называют меня...
//You can call me... Можешь называть меня...
//How do you spell your name? Как пишется ваше имя?
//Haven't we met (before)? Разве мы уже не встречались (раньше)?
//I'm going to call you Bill for short. Для краткости я буду звать вас Билл.
//I think we've already met. Думаю, мы уже встречались.
//Nice to see you. / Good to see you. Рад тебя видеть.
//I don't mind - ничего не имею против
//I think so - согласен
//You'll make it - у тебя получится
//That's the whole point - в этом все и дело
//Easy - полегче
//Calm down - успокойся
//It makes things easier - так легче (переживать/переносить боль)
//I havent given it much thought - я пока/еще не думал об этом ( насчет планов на будущее)
//It serves you/smb. right - так себе/кому-либо и надо.
//You'll hear from me - я дам вам знать/сообщу о себе
//It's going to be all right - все будет хорошо
//You bet - еще спрашиваешь!
//Sounds good to me - это меня устраивает
//Hear me out - выслушайте меня
//I couldn't reach you - я не мог дозвониться до тебя
//Let happen whatever would happen - будь, что будет
//It never crossed my mind, (that) - мне никогда не приходило в голову, что...
//Don't mention that - не надо об этом
//Get out of my way - уйди с дороги
//Get lost - исчезни
//You have a point there - тут вы правы/тоже верно
//I mean it - серьезно
//Let's get to the point/Let's hold a reason - давайте ближе к делу
//So far so good - пока что все идет хорошо
//It's not that I don't - не то, чтобы я не...
//Я рассчитываю на тебя - I rely on you.
//Когда встретимся? - When shall we cross (meet)?
//Удели мне пару минут - Spare me two minutes
//Я бы хотел... - I would like (I'd like)
//Ты завтра свободен? - Are you free tomorrow
//Ты сегодня вечером свободен? - Free this evening?
//Ну ты крут! - You're cool!
//Ну ты чудак! - You're a strange dude!
//У него не все дома - He doesn't have all his buttons
//Как дела? - How are you? (How are you getting on?)
//Что нового? - What's news?
//Чем занимаешься? - What are you going?
//Какого черта ты тут делаешь? - What the hell are you doing here?
//Что стряслось? - What has gone down?
//I'm really buzzing! - У меня все просто здорово.
//I'm having a really peachy time! - Я превосходно провожу время.
//I'm walking on air! - Я на седьмом небе от счастья!
//She threw a wobbly. - Она не в себе.
//I had a complete fit. - Меня не на шутку разозлили.
//She blew her top. - У нее "крыша" поехала.
//I have no idea - Понятия не имею
//I mean it! - Я серьёзно
//I wish I knew - Хотел бы я знать!
//It' s none of your business - Не твоё дело
//What are you driving at? - К чему ты клонишь?
//What are you talking about? - О чём ты!
//What for? - Зачем?
//What of it? - И что из этого?
//You can take it from me - Можешь мне поверить
//It is urgent. - Это срочно.
//I'll see about it. - Я об этом позабочусь.
//What a pity! - Как жаль!
//It didn't work out. - Ничего не вышло.
//It was a success. - Мы успешно справились.
//That's so true. Совершенно верно.
//That's for sure. Это точно. / Это наверняка.
//Tell me about it! (Разговорная форма.) Еще как! / Я прекрасно понимаю! / и т.п.
//You're absolutely right. Ты совершенно прав.
//Absolutely! Еще как! / Да, конечно! / Так точно! / и т.п.
//No, I don't think so. Нет, не думаю.
//I don't see it that way. Я вижу это по-другому.
//I can't share your point of view. Не могу разделить твою точку зрения.
//I'm afraid it isn't right. Боюсь, это неправильно.
//You can't really be serious! Ты ведь не серьезно?
//Take care! - Береги себя
//Good luck! - Удачи
//All the best! - Всего наилучшего
//Have a good trip - Счастливого пути
//Write to us - Пиши нам
//Call me - Звони мне
//I'm sorry to see you go - Жаль, что вы уходите
//I've enjoyed seeing you - Был рад вас видеть
//Come back soon - Возвращайтесь поскорее
//My regards to the family - привет семье

