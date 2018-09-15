/*
 * Copyright (c) 2018 Sanjar Khodjaev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package uz.pentoligy.smartbackgroundmanagerdemo.entity

data class MovieItem private constructor(
        val id: Int,
        val title: String,
        val poster: String,
        val backgroundPoster: String
) {

    companion object {

        val upcomingMoviesItems: ArrayList<MovieItem> = ArrayList<MovieItem>().apply {
            add(MovieItem(1000, "Avengers: Infinity War", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"))
            add(MovieItem(1001, "Deadpool 2", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/rLIIGGCdj6EGxZKY8aqIYBfsib6.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/3P52oz9HPQWxcwHOwxtyrVV1LKi.jpg"))
            add(MovieItem(1002, "Solo: A Star Wars Story", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/3IGbjc5ZC5yxim5W0sFING2kdcz.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/96B1qMN9RxrAFu6uikwFhQ6N6J9.jpg"))
            add(MovieItem(1003, "Show Dogs", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/5LLxjWWQUW77TAeiCPW7ZpXLAec.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/pk1zexoUCtjFs761W48QyIPzwCj.jpg"))
            add(MovieItem(1004, "Disobedience", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/cPsa2odqNRz4WJVd4f8yZWui3iu.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/bETpSwTyxGK7xPjav67HlW6f8C9.jpg"))
            add(MovieItem(1005, "First Reformed", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/p2UylV0hPefEuWPFMo1r56vu2nb.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/vhwb9aFrKSfFkYDyA2G23FzqVqK.jpg"))
            add(MovieItem(1006, "How to Talk to Girls at Parties", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/tyCt5dQ2yhfEtWcP3KqMgtFk7s0.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/uBz80hwNN5eNkbmpC6MtRzAlVHp.jpg"))
            add(MovieItem(1007, "Adrift", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/5gLDeADaETvwQlQow5szlyuhLbj.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/64jAqTJvrzEwncD3ARZdqYLcqbc.jpg"))
            add(MovieItem(1008, "American Animals", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/x73Ln1F9uznu7dArUFdds2Hxsll.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/3ccBOsbVpgwN9K5whd2UB9ACebG.jpg"))
            add(MovieItem(1009, "Future World", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/iO8KeFvmRvrzpD2kvlzbhPo60dX.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/vRkupior3ni5NPLnIicpjRKYYVw.jpg"))
            add(MovieItem(1010, "Pope Francis: A Man of His Word", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/lH1QleR9NzzVouL4gp76CRnu9I6.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/lUVRn6CC3voutG905c8qTIAa7QL.jpg"))
            add(MovieItem(1011, "Action Point", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/5lqJx0uNKrD1cEKgaqF1LBsLAoi.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/gdkDkgS9DUcH46Gl87j6wIE5zfN.jpg"))
        }

        val marvelCinematicUniverse1MoviesItems: ArrayList<MovieItem> = ArrayList<MovieItem>().apply {
            add(MovieItem(2000, "Iron Man", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/848chlIWVT41VtAAgyh9bWymAYb.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/ZQixhAZx6fH1VNafFXsqa1B8QI.jpg"))
            add(MovieItem(2001, "The Incredible Hulk", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/vcFeElC8eXI668WSIxcDNkJSBxv.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/cVpBJxRBIwTjajKQ08TE6BINTEu.jpg"))
            add(MovieItem(2002, "Iron Man 2", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/ArqpkNYGfcTIA6umWt6xihfIZZv.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/jxdSxqAFrdioKgXwgTs5Qfbazjq.jpg"))
            add(MovieItem(2003, "Thor", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/bIuOWTtyFPjsFDevqvF3QrD1aun.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/LvmmDZxkTDqp0DX7mUo621ahdX.jpg"))
            add(MovieItem(2004, "Captain America: The First Avenger", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/vSNxAJTlD0r02V9sPYpOjqDZXUK.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/pmZtj1FKvQqISS6iQbkiLg5TAsr.jpg"))
            add(MovieItem(2005, "Avengers", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/cezWGskPY5x7GaglTTRN4Fugfb8.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/hbn46fQaRmlpBuUrEiFqv0GDL6Y.jpg"))
            add(MovieItem(2006, "Iron Man 3", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/1Ilv6ryHUv6rt9zIsbSEJUmmbEi.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/n9X2DKItL3V0yq1q1jrk8z5UAki.jpg"))
            add(MovieItem(2007, "Thor: The Dark World", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/bnX5PqAdQZRXSw3aX3DutDcdso5.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/3FweBee0xZoY77uO1bhUOlQorNH.jpg"))
            add(MovieItem(2008, "Captain America: The Winter Soldier", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/5TQ6YDmymBpnF005OyoB7ohZps9.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/4qfXT9BtxeFuamR4F49m2mpKQI1.jpg"))
            add(MovieItem(2009, "Guardians of the Galaxy", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/y31QB9kn3XSudA15tV7UWQ9XLuW.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/bHarw8xrmQeqf3t8HpuMY7zoK4x.jpg"))
            add(MovieItem(2010, "Avengers: Age of Ultron", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/t90Y3G8UGQp0f0DrP60wRu9gfrH.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/rFtsE7Lhlc2jRWF7SRAU0fvrveQ.jpg"))
            add(MovieItem(2011, "Ant-Man", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/D6e8RJf2qUstnfkTslTXNTUAlT.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/kvXLZqY0Ngl1XSw7EaMQO0C1CCj.jpg"))
        }

        val marvelCinematicUniverse2MoviesItems: ArrayList<MovieItem> = ArrayList<MovieItem>().apply {
            add(MovieItem(3000, "Captain America: Civil War", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/kSBXou5Ac7vEqKd97wotJumyJvU.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/m5O3SZvQ6EgD5XXXLPIP1wLppeW.jpg"))
            add(MovieItem(3001, "Doctor Strange", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/4PiiNGXj1KENTmCBHeN6Mskj2Fq.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/tFI8VLMgSTTU38i8TIsklfqS9Nl.jpg"))
            add(MovieItem(3002, "Guardians of the Galaxy Vol. 2", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/y4MBh0EjBlMuOzv9axM4qJlmhzz.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/aJn9XeesqsrSLKcHfHP4u5985hn.jpg"))
            add(MovieItem(3003, "Spider-Man: Homecoming", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/c24sv2weTHPsmDa7jEMN0m2P3RT.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/vc8bCGjdVp0UbMNLzHnHSLRbBWQ.jpg"))
            add(MovieItem(3004, "Thor: Ragnarok", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/rzRwTcFvttcN1ZpX2xv4j3tSdJu.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/kaIfm5ryEOwYg8mLbq8HkPuM1Fo.jpg"))
            add(MovieItem(3005, "Black Panther", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/uxzzxijgPIY7slzFvMotPv8wjKA.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/AlFqBwJnokrp9zWTXOUv7uhkaeq.jpg"))
            add(MovieItem(3006, "Avengers: Infinity War", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"))
            add(MovieItem(3007, "Ant-Man and the Wasp", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/rv1AWImgx386ULjcf62VYaW8zSt.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/qw0v2yLkH96pjpsltSFGycILYgp.jpg"))
            add(MovieItem(3008, "Captain Marvel", "https://image.tmdb.org/t/p/w300_and_h450_bestv2/2ClU44E07dC6tPYhxJA8hbWDUAQ.jpg", "https://image.tmdb.org/t/p/w1400_and_h450_face/1MbLKqHXBw2NJsd5Q2OvtO8qUZV.jpg"))
        }

    }
}