<img src="https://github.com/devrath/devrath/blob/master/images/kotlin_logo.png" align="right" title="Kotlin Logo" width="120">

# droid-network-observer 🧞‍
[![life-cycle-aware](https://img.shields.io/badge/life--cycle--aware-red)](https://developer.android.com/topic/libraries/architecture/lifecycle) 
[![Kotlin](https://img.shields.io/badge/connectivity--manager-brightgreen)](https://developer.android.com/reference/android/net/ConnectivityManager)
[![Coroutines](https://img.shields.io/badge/Coroutines-1.6.0-red)](https://kotlinlang.org/docs/reference/coroutines-overview.html) [![DaggerHilt](https://img.shields.io/badge/DaggerHilt-2.40-blue)](https://developer.android.com/training/dependency-injection/hilt-android)
[![Live data](https://img.shields.io/badge/live--data-orange)](https://developer.android.com/topic/libraries/architecture/livedata)
[![Multi module](https://img.shields.io/badge/Multi--Module-blueviolet)](https://developer.android.com/topic/modularization)

## **`𝚄𝚜𝚎 𝚌𝚊𝚜𝚎`** 🚀
🏷️ In android projects it is common to communicate with a network and get data from a remote server or post data into remote server. </br>
🏷️ There is a common case to check connectivity before performing either of the case. </br>
🏷️ Sometims when the connectivity fails and gets connected, again we need to show a notification just like how youtube does. </br>

## **`𝙷𝚘𝚠 𝚠𝚎 𝚞𝚜𝚎𝚍 𝚝𝚘 𝚒𝚖𝚙𝚕𝚎𝚖𝚎𝚗𝚝 𝚒𝚗 𝚕𝚎𝚐𝚊𝚌𝚢 𝚙𝚛𝚘𝚓𝚎𝚌𝚝𝚜`** 💡
🏷️ Everytime we used to call a API, we check connectivity and say if we have multiple API's, we do the same check multiple times. </br>
🏷️ And to monitor the connectivity change we used to listen to broadcast from the android system</br>

## **`𝙱𝚎𝚝𝚝𝚎𝚛 𝙸𝚖𝚙𝚕𝚎𝚖𝚎𝚗𝚝𝚊𝚝𝚒𝚘𝚗`** 💡
🏷️ With the introduction of live data and the life-cycle observers, we can have a better implementation to monitor and react to the network changes. </br>
🏷️ We can store the changes in a variable and just use that to determine the state of the network instead of checking network everytime. </br>
🏷️ By this way is efficient and less error prone and the is single source of truth for current connectivity state. </br>

## **`𝙾𝚞𝚝𝚙𝚞𝚝`** 🧿
<p align="center">
<img src="https://github.com/devrath/droid-network-observer/blob/main/assets/recording.gif" width="220" height="460"/>
</p>

## **`𝚂𝚞𝚙𝚙𝚘𝚛𝚝`** ☕
If you feel like support me a coffee for my efforts, I would greatly appreciate it.</br>
<a href="https://www.buymeacoffee.com/devrath" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/yellow_img.png" alt="Buy Me A Coffee" style="height: 41px !important;width: 174px !important;box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;-webkit-box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;" ></a>

## **`𝙲𝚘𝚗𝚝𝚛𝚒𝚋𝚞𝚝𝚎`** 🙋‍♂️
Read [contribution guidelines](CONTRIBUTING.md) for more information regarding contribution.

## **`𝙵𝚎𝚎𝚍𝚋𝚊𝚌𝚔`** ✍️ 
Feature requests are always welcome, [File an issue here](https://github.com/devrath/droid-network-observer/issues/new).

## **`𝙵𝚒𝚗𝚍 𝚝𝚑𝚒𝚜 𝚙𝚛𝚘𝚓𝚎𝚌𝚝 𝚞𝚜𝚎𝚏𝚞𝚕`** ? ❤️
Support it by clicking the ⭐ button on the upper right of this page. ✌️

## **`𝙻𝚒𝚌𝚎𝚗𝚜𝚎`** ![Licence](https://img.shields.io/github/license/google/docsy) :credit_card:
This project is licensed under the Apache License 2.0 - see the [LICENSE](https://github.com/devrath/droid-network-observer/blob/main/LICENSE) file for details


<p align="center">
<a><img src="https://forthebadge.com/images/badges/built-for-android.svg"></a>
</p>

