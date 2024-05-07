# Java Collection adalah kumpulan objek yang mirip dengan array tetapi lebih fleksibel dan kuat. Java menyediakan berbagai struktur data koleksi yang dapat digunakan tanpa memerlukan pustaka tambahan.

# Iterable & Iterator Interface:

# Iterable Interface: Ini adalah induk untuk semua koleksi di Java kecuali Map. Berguna untuk mendukung pengulangan menggunakan perulangan for-each.

# Iterator Interface: Iterator memungkinkan akses elemen dalam koleksi secara berurutan. Sebelum Java 5, iterator digunakan untuk iterasi koleksi secara manual.

# Collection Interface:

# Collection Interface: Induk kelas untuk semua koleksi di Java. Digunakan untuk manipulasi data seperti penambahan, penghapusan, dan pemeriksaan isi.

# List Interface:

# List Interface: Struktur data koleksi yang memungkinkan duplikat elemen dan menyimpan elemen dalam urutan tertentu. Menggunakan indeks untuk mengakses elemen.

# ArrayList: Implementasi List menggunakan array. Dinamis dalam ukuran dan cepat untuk operasi penambahan jika kapasitas array cukup.

# LinkedList: Implementasi List dengan struktur data Double Linked List. Cepat untuk penambahan di akhir tetapi lambat untuk akses acak.

# Queue Interface:

# Queue Interface: Implementasi struktur data antrian atau FIFO (First In First Out). Mengatur elemen sesuai urutan masuk.

# ArrayDeque vs LinkedList vs PriorityQueue: ArrayDeque dan LinkedList adalah implementasi antrian dengan perbedaan struktur data, sedangkan PriorityQueue menggunakan array dengan pengurutan menggunakan Comparable atau Comparator.

# Deque Interface:

# Deque Interface: Singkatan dari double ended queue yang mendukung operasi dari depan dan belakang. Selain FIFO, juga mendukung LIFO (Last In First Out).

# Map Interface:

# Map Interface: Struktur data yang memetakan antara kunci dan nilai. Kunci harus unik dan setiap kunci hanya dapat memiliki satu nilai.

# HashMap: Implementasi Map yang menggunakan hashCode() function untuk mendistribusikan kunci. Penting untuk membuat hashCode() unik untuk menghindari performa yang lambat.
