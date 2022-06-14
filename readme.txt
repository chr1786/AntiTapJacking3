Pada Android level 30+, terdapat perubahan policy untuk mengetahui package yang ada terinstall.
Dimana, jika ingin menampilkan/ melihat seluruh package yang ada, harus secara eksplisit menyebutkan nama packagenya pada <queries> di manifest.
Atau menggunakan permission android.permission.QUERY_ALL_PACKAGES, namun apk yang menggunakan permission tersebut saat upload ke PlayStore akan di review oleh Google
Apakah memenuhi ketentuan penggunaannya atau tidak, jika tidak maka apk akan ditolak.

Referensi : 
- https://medium.com/androiddevelopers/package-visibility-in-android-11-cc857f221cd9
- https://developer.android.com/about/versions/11/privacy/package-visibility