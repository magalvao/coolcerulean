package me.keyo.cb


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import me.keyo.cb.data.local.CategoriesLocalEndpoint
import me.keyo.cb.data.model.ProductCategory
import me.keyo.cb.ui.search.SearchAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class UITests {

    @Rule @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private lateinit var categoryList: List<ProductCategory>

    @Before
    fun initCategories() {
        categoryList = CategoriesLocalEndpoint(activityRule.activity).getCategories()
    }

    @Test
    fun fragmentsNavigation() {

        onView(withId(R.id.navigation_wishlist))
            .perform(click())
        onView(withId(R.id.text_tbd))
            .check(matches(withText("This section is under construction!")))

        onView(withId(R.id.navigation_bag))
            .perform(click())
        onView(withId(R.id.text_tbd))
            .check(matches(withText("This section is under construction!")))

        onView(withId(R.id.navigation_orders))
            .perform(click())
        onView(withId(R.id.text_tbd))
            .check(matches(withText("This section is under construction!")))

        onView(withId(R.id.navigation_more))
            .perform(click())
        onView(withId(R.id.text_tbd))
            .check(matches(withText("This section is under construction!")))

        onView(withId(R.id.navigation_home))
            .perform(click())
    }

    @Test
    fun testCategoriesOnHome() {
        categoryList.forEach {
            onView(withText(it.name))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun testCategoriesSearch() {
        categoryList.forEach {
            onView(withText(it.name))
                .perform(click())
            onView(withId(R.id.fagment_search_edit))
                .check(matches(withText(it.tag)))



            onView(withId(R.id.navigation_home))
                .perform(click())
        }
    }

    @Test
    fun testSearchPagination() {
        onView(withId(R.id.fagment_home_search_edit))
            .perform(typeText("iphone"), pressImeActionButton())

        

        onView(withId(R.id.fragment_search_recyclerview))
            .perform(
                RecyclerViewActions.scrollToPosition<SearchAdapter.SearchViewHolder>(25)
            )

        onView(withId(R.id.fragment_search_recyclerview))
            .perform(
                RecyclerViewActions.actionOnItem<SearchAdapter.SearchViewHolder>(
                hasDescendant(withId(R.id.view_paging_next)), click())
            );
    }
}

