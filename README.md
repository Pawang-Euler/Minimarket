# Minimarket
Assosiation rules for market basket analysis using Trie in Java implementation by Pawang Euler.

## Deskripsi Aplikasi
### Input Dataset
Input dataset berupa sebuah file berekstensi .tcsv yang berisi kumpulan data transaksi random dengan sejumlah barang yang terjual.
 #### Jenis file dataset
 Jenis file yang akan digunakan pada dataset adalah dengan ekstensi .tscv (transaction comma separated values), bentuk file ini didesain khusus untuk menyimpan data transaksi yang dibutuhkan untuk menjadi dataset program.

Desain tcsv ini mengambil inspirasi dari tipe file csv, namun dengan perubahan utama yaitu dimana kolom terakhir bertindak sebagai suatu array. Hal ini dikarenakan tipe file csv tidak dapat merepresentasikan suatu array yang dibutuhkan untuk menyimpan data pembelian.

Bentuk dari tipe file tcsv adalah seperti dibawah ini,

| `Id` | `Products` |   |
|----|----------|---|

Contoh penggunaan dalam menyimpan transaksi,

> 

    id,products
    1,A,B,C
    2,A,B
    3,A,C,D,E
    4,E

File di atas memiliki struktur kolom dengan kolom id sebagai kolom non-array dan products sebagai kolom array.

Penulisan products terurut secara ascending serta tidak ada duplikasi dari setiap products yang ada.
## Builds
[![Build Status](https://travis-ci.org/Pawang-Euler/Minimarket.svg?branch=master)](https://travis-ci.org/Pawang-Euler/Minimarket)
