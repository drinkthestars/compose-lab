package com.goofy.goober.composelab

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.goofy.goober.composelab.activity.AnimatedText
import com.goofy.goober.composelab.animations.OnPlacedModifierAlignmentChange

@Composable
fun ComposeSourceDemosLab() {
    val navController = rememberNavController()

    LabScaffold(
        navController = navController,
        labs = MiscLab,
        startDestination = Screen.ComposeSourceDemos
    ) {
        composable(Screen.Animations.AlignmentChange.route) { OnPlacedModifierAlignmentChange() }
        composable(Screen.Animations.AnimatedText.route) {
            AnimatedText(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 80.dp, horizontal = 20.dp),
                text = "To see a World in a Grain of Sand\n\n" +
                    "And a Heaven in a Wild Flower \n\n" +
                    "Hold Infinity in the palm of your hand \n\n" +
                    "And Eternity in an hour\n\n" +
                    "A Robin Red breast in a Cage\n\n" +
                    "Puts all Heaven in a Rage \n\n" +
                    "A Dove house filld with Doves & Pigeons\n\n" +
                    "Shudders Hell thr' all its regions \n\n" +
                    "A dog starvd at his Masters Gate\n\n" +
                    "Predicts the ruin of the State \n\n" +
                    "A Horse misusd upon the Road\n\n" +
                    "Calls to Heaven for Human blood \n\n" +
                    "Each outcry of the hunted Hare\n\n" +
                    "A fibre from the Brain does tear \n\n" +
                    "A Skylark wounded in the wing \n\n" +
                    "A Cherubim does cease to sing \n\n" +
                    "The Game Cock clipd & armd for fight\n\n" +
                    "Does the Rising Sun affright \n\n" +
                    "Every Wolfs & Lions howl\n\n" +
                    "Raises from Hell a Human Soul \n\n" +
                    "The wild deer, wandring here & there \n\n" +
                    "Keeps the Human Soul from Care \n\n" +
                    "The Lamb misusd breeds Public Strife\n\n" +
                    "And yet forgives the Butchers knife \n\n" +
                    "The Bat that flits at close of Eve\n\n" +
                    "Has left the Brain that wont Believe\n\n" +
                    "The Owl that calls upon the Night\n\n" +
                    "Speaks the Unbelievers fright\n\n" +
                    "He who shall hurt the little Wren\n\n" +
                    "Shall never be belovd by Men \n\n" +
                    "He who the Ox to wrath has movd\n\n" +
                    "Shall never be by Woman lovd\n\n" +
                    "The wanton Boy that kills the Fly\n\n" +
                    "Shall feel the Spiders enmity \n\n" +
                    "He who torments the Chafers Sprite\n\n" +
                    "Weaves a Bower in endless Night \n\n" +
                    "The Catterpiller on the Leaf\n\n" +
                    "Repeats to thee thy Mothers grief \n\n" +
                    "Kill not the Moth nor Butterfly \n\n" +
                    "For the Last Judgment draweth nigh \n\n" +
                    "He who shall train the Horse to War\n\n" +
                    "Shall never pass the Polar Bar \n\n" +
                    "The Beggars Dog & Widows Cat \n\n" +
                    "Feed them & thou wilt grow fat \n\n" +
                    "The Gnat that sings his Summers Song\n\n" +
                    "Poison gets from Slanders tongue \n\n" +
                    "The poison of the Snake & Newt\n\n" +
                    "Is the sweat of Envys Foot \n\n" +
                    "The poison of the Honey Bee\n\n" +
                    "Is the Artists Jealousy\n\n" +
                    "The Princes Robes & Beggars Rags\n\n" +
                    "Are Toadstools on the Misers Bags \n\n" +
                    "A Truth thats told with bad intent\n\n" +
                    "Beats all the Lies you can invent \n\n" +
                    "It is right it should be so \n\n" +
                    "Man was made for Joy & Woe \n\n" +
                    "And when this we rightly know \n\n" +
                    "Thro the World we safely go \n\n" +
                    "Joy & Woe are woven fine \n\n" +
                    "A Clothing for the soul divine \n\n" +
                    "Under every grief & pine\n\n" +
                    "Runs a joy with silken twine \n\n" +
                    "The Babe is more than swadling Bands\n\n" +
                    "Throughout all these Human Lands\n\n" +
                    "Tools were made & Born were hands \n\n" +
                    "Every Farmer Understands\n\n" +
                    "Every Tear from Every Eye\n\n" +
                    "Becomes a Babe in Eternity \n\n" +
                    "This is caught by Females bright\n\n" +
                    "And returnd to its own delight \n\n" +
                    "The Bleat the Bark Bellow & Roar \n\n" +
                    "Are Waves that Beat on Heavens Shore \n\n" +
                    "The Babe that weeps the Rod beneath\n\n" +
                    "Writes Revenge in realms of Death \n\n" +
                    "The Beggars Rags fluttering in Air\n\n" +
                    "Does to Rags the Heavens tear \n\n" +
                    "The Soldier armd with Sword & Gun \n\n" +
                    "Palsied strikes the Summers Sun\n\n" +
                    "The poor Mans Farthing is worth more\n\n" +
                    "Than all the Gold on Africs Shore\n\n" +
                    "One Mite wrung from the Labrers hands\n\n" +
                    "Shall buy & sell the Misers Lands \n\n" +
                    "Or if protected from on high \n\n" +
                    "Does that whole Nation sell & buy \n\n" +
                    "He who mocks the Infants Faith\n\n" +
                    "Shall be mockd in Age & Death \n\n" +
                    "He who shall teach the Child to Doubt\n\n" +
                    "The rotting Grave shall neer get out \n\n" +
                    "He who respects the Infants faith\n\n" +
                    "Triumphs over Hell & Death \n\n" +
                    "The Childs Toys & the Old Mans Reasons\n\n" +
                    "Are the Fruits of the Two seasons \n\n" +
                    "The Questioner who sits so sly \n\n" +
                    "Shall never know how to Reply \n\n" +
                    "He who replies to words of Doubt\n\n" +
                    "Doth put the Light of Knowledge out \n\n" +
                    "The Strongest Poison ever known\n\n" +
                    "Came from Caesars Laurel Crown \n\n" +
                    "Nought can Deform the Human Race\n\n" +
                    "Like to the Armours iron brace \n\n" +
                    "When Gold & Gems adorn the Plow\n\n" +
                    "To peaceful Arts shall Envy Bow \n\n" +
                    "A Riddle or the Crickets Cry\n\n" +
                    "Is to Doubt a fit Reply \n\n" +
                    "The Emmets Inch & Eagles Mile\n\n" +
                    "Make Lame Philosophy to smile \n\n" +
                    "He who Doubts from what he sees\n\n" +
                    "Will neer Believe do what you Please \n\n" +
                    "If the Sun & Moon should Doubt \n\n" +
                    "Theyd immediately Go out \n\n" +
                    "To be in a Passion you Good may Do \n\n" +
                    "But no Good if a Passion is in you \n\n" +
                    "The Whore & Gambler by the State\n\n" +
                    "Licencd build that Nations Fate \n\n" +
                    "The Harlots cry from Street to Street \n\n" +
                    "Shall weave Old Englands winding Sheet \n\n" +
                    "The Winners Shout the Losers Curse \n\n" +
                    "Dance before dead Englands Hearse \n\n" +
                    "Every Night & every Morn\n\n" +
                    "Some to Misery are Born \n\n" +
                    "Every Morn and every Night\n\n" +
                    "Some are Born to sweet delight \n\n" +
                    "Some are Born to sweet delight \n\n" +
                    "Some are Born to Endless Night \n\n" +
                    "We are led to Believe a Lie\n\n" +
                    "When we see not Thro the Eye\n\n" +
                    "Which was Born in a Night to perish in a Night \n\n" +
                    "When the Soul Slept in Beams of Light \n\n" +
                    "God Appears & God is Light\n\n" +
                    "To those poor Souls who dwell in Night \n\n" +
                    "But does a Human Form Display\n\n" +
                    "To those who Dwell in Realms of day",
                fontFamily = FontFamily.Monospace,
                characterDelayMs = 40
            )
        }
    }
}

private val MiscLab = listOf(
    ComposableLab(screen = Screen.Animations.AlignmentChange),
    ComposableLab(screen = Screen.Animations.AnimatedText)
)
