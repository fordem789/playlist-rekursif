/*
 * ============================================================================
 * TUGAS KELOMPOK KE-3 (WEEK 8)
 * Mata Kuliah   : COSC6025 - Data Structures and Algorithm Analysis
 * Topik         : Algorithmic Analysis, Mathematical Induction, and Recursive Functions
 * 
 * IDENTITAS KELOMPOK (GROUP-5)
 * 1. DAFA' RIFQI AL-PADO      - 2902818114
 * 2. AHMAD RIDHO RAMADHAN     - 2902827296
 * 3. FEDRO TIMOTHY PURBA      - 2902812501
 * 4. SYAROFI ISHMA YAHYA      - 2902817300
 * 5. BELLA PUTRI CANTIQCA     - 2902810332
 * Kelas / Prodi : Computer Science
 * ============================================================================
 */

import java.util.Locale;

/**
 * Class PlaylistRekursif
 * Mengembangkan sistem manajemen playlist musik dengan menambahkan kemampuan
 * analisis rekursif dan pengukuran efisiensi algoritmik (time measurement).
 * 
 * Catatan: Class Lagu diambil dari Tugas Kelompok sebelumnya (lihat PlaylistArray.java).
 * Jika dijalankan secara mandiri di satu file, pastikan class Lagu berada di package yang sama
 * atau hapus komentar class Lagu di bagian bawah file ini.
 */
public class PlaylistRekursif {

    // Atribut array untuk menyimpan daftar objek Lagu dalam playlist
    private Lagu[] playlist;

    // Constructor PlaylistRekursif
    public PlaylistRekursif(Lagu[] playlist) {
        this.playlist = playlist;
    }

    public Lagu[] getPlaylist() {
        return playlist;
    }

    /**
     * 1. Fungsi Rekursif untuk menghitung total durasi playlist.
     * 
     * Base Case     : Jika n <= 0, kembalikan 0.0 (tidak ada lagu yang dihitung).
     * Recursive Step: Durasi lagu ke-(n-1) + totalDurasi(list, n - 1).
     * Kompleksitas  : O(n) - Linear terhadap jumlah lagu n.
     *
     * @param list Array objek Lagu
     * @param n    Jumlah lagu yang dihitung
     * @return Total durasi seluruh lagu dalam menit
     */
    public static double totalDurasi(Lagu[] list, int n) {
        // Base case: jika tidak ada elemen tersisa
        if (n <= 0) {
            return 0.0;
        }
        // Recursive case: tambahkan durasi elemen saat ini dengan hasil rekursi elemen sebelumnya
        return list[n - 1].getDurasi() + totalDurasi(list, n - 1);
    }

    /**
     * 2. Fungsi Rekursif untuk menampilkan daftar lagu secara terbalik (reverse traversal).
     * 
     * Base Case     : Jika index < 0, hentikan pemanggilan rekursif (return).
     * Recursive Step: Cetak lagu pada index saat ini, lalu panggil tampilkanMundur(list, index - 1).
     * Kompleksitas  : O(n) - Linear terhadap jumlah lagu n.
     *
     * @param list  Array objek Lagu
     * @param index Indeks elemen saat ini (dimulai dari n - 1 menuju 0)
     */
    public static void tampilkanMundur(Lagu[] list, int index) {
        // Base case: jika indeks sudah melewati elemen pertama (kurang dari 0)
        if (index < 0) {
            return;
        }
        // Cetak nomor urut dan judul lagu pada indeks saat ini
        System.out.println((index + 1) + ". " + list[index].getJudul());

        // Recursive case: panggil fungsi untuk indeks sebelumnya (index - 1)
        tampilkanMundur(list, index - 1);
    }

    /**
     * 3. Fungsi Rekursif untuk mencari nilai durasi terpanjang dalam playlist.
     * 
     * Base Case     : Jika index == 0, kembalikan durasi lagu pada indeks ke-0.
     * Recursive Step: Bandingkan durasi lagu pada index saat ini dengan hasil
     *                 cariDurasiTerpanjang(list, index - 1), kembalikan nilai maksimumnya.
     * Kompleksitas  : O(n) - Linear terhadap jumlah lagu n.
     *
     * @param list  Array objek Lagu
     * @param index Indeks elemen saat ini (dimulai dari n - 1 menuju 0)
     * @return Nilai durasi lagu terpanjang (double)
     */
    public static double cariDurasiTerpanjang(Lagu[] list, int index) {
        // Base case: jika hanya tersisa elemen pertama (indeks 0)
        if (index == 0) {
            return list[0].getDurasi();
        }
        // Recursive case: cari durasi terpanjang dari sisa elemen sebelumnya
        double maksSebelumnya = cariDurasiTerpanjang(list, index - 1);

        // Bandingkan durasi elemen saat ini dengan durasi maksimum sebelumnya
        return Math.max(list[index].getDurasi(), maksSebelumnya);
    }

    /**
     * Fungsi bantu rekursif untuk mendapatkan objek Lagu yang memiliki durasi terpanjang
     * agar judul dan artis lagu terpanjang dapat ditampilkan sesuai format output.
     */
    public static Lagu cariLaguTerpanjangObjek(Lagu[] list, int index) {
        if (index == 0) {
            return list[0];
        }
        Lagu laguMaksSebelumnya = cariLaguTerpanjangObjek(list, index - 1);
        if (list[index].getDurasi() >= laguMaksSebelumnya.getDurasi()) {
            return list[index];
        } else {
            return laguMaksSebelumnya;
        }
    }

    /**
     * Fungsi untuk menjalankan analisis rekursif dan mencetak hasil beserta waktu eksekusi.
     */
    public static void jalankanAnalisisUtama(Lagu[] daftarLagu, int jumlahLagu) {
        System.out.println("=== ANALISIS REKURSIF PLAYLIST ===");
        System.out.println("Jumlah lagu : " + jumlahLagu);

        // 1. Pengukuran waktu eksekusi totalDurasi()
        long startTotal = System.nanoTime();
        double total = totalDurasi(daftarLagu, jumlahLagu);
        long endTotal = System.nanoTime();
        double waktuTotalMs = (endTotal - startTotal) / 1_000_000.0;

        // 2. Pengukuran waktu eksekusi cariDurasiTerpanjang()
        long startMax = System.nanoTime();
        double durasiMaks = cariDurasiTerpanjang(daftarLagu, jumlahLagu - 1);
        long endMax = System.nanoTime();
        double waktuMaxMs = (endMax - startMax) / 1_000_000.0;

        Lagu laguTerpanjang = cariLaguTerpanjangObjek(daftarLagu, jumlahLagu - 1);

        System.out.printf(Locale.US, "Total durasi : %.2f menit%n", total);
        System.out.printf(Locale.US, "Lagu terpanjang : \"%s\" - %s (%.2f menit)%n",
                laguTerpanjang.getJudul(), laguTerpanjang.getArtis(), durasiMaks);

        // 3. Pengukuran waktu eksekusi tampilkanMundur()
        System.out.println("Daftar lagu (ditampilkan terbalik):");
        long startMundur = System.nanoTime();
        tampilkanMundur(daftarLagu, jumlahLagu - 1);
        long endMundur = System.nanoTime();
        double waktuMundurMs = (endMundur - startMundur) / 1_000_000.0;

        System.out.println();
        System.out.printf(Locale.US, "Execution Time (totalDurasi): %.4f ms%n", waktuTotalMs);
        System.out.printf(Locale.US, "Execution Time (tampilkanMundur): %.4f ms%n", waktuMundurMs);
        System.out.printf(Locale.US, "Execution Time (cariDurasiTerpanjang): %.4f ms%n", waktuMaxMs);
    }

    /**
     * Fungsi untuk menjalankan eksperimen perbandingan waktu eksekusi pada n = 3, 5, dan 10 lagu.
     */
    public static void jalankanEksperimen(Lagu[] dataLengkap) {
        int[] variasiUkuran = {3, 5, 10};

        System.out.println("\n======================================================================");
        System.out.println("   EKSPERIMEN PENGUKURAN WAKTU EKSEKUSI (n = 3, 5, dan 10 Lagu)");
        System.out.println("======================================================================");

        for (int n : variasiUkuran) {
            System.out.println("\n--- Pengujian untuk n = " + n + " lagu ---");

            long t1Start = System.nanoTime();
            double total = totalDurasi(dataLengkap, n);
            long t1End = System.nanoTime();

            System.out.println("Daftar lagu (ditampilkan terbalik):");
            long t2Start = System.nanoTime();
            tampilkanMundur(dataLengkap, n - 1);
            long t2End = System.nanoTime();

            long t3Start = System.nanoTime();
            double maks = cariDurasiTerpanjang(dataLengkap, n - 1);
            long t3End = System.nanoTime();

            double durasiT1Ms = (t1End - t1Start) / 1_000_000.0;
            double durasiT2Ms = (t2End - t2Start) / 1_000_000.0;
            double durasiT3Ms = (t3End - t3Start) / 1_000_000.0;

            System.out.printf(Locale.US, "Total Durasi         : %.2f menit%n", total);
            System.out.printf(Locale.US, "Durasi Terpanjang    : %.2f menit%n", maks);
            System.out.printf(Locale.US, "Waktu totalDurasi()          : %.4f ms (%d ns)%n", durasiT1Ms, (t1End - t1Start));
            System.out.printf(Locale.US, "Waktu tampilkanMundur()      : %.4f ms (%d ns)%n", durasiT2Ms, (t2End - t2Start));
            System.out.printf(Locale.US, "Waktu cariDurasiTerpanjang() : %.4f ms (%d ns)%n", durasiT3Ms, (t3End - t3Start));
        }
    }

    public static void main(String[] args) {
        // Inisialisasi 10 data objek Lagu dalam array
        // 5 lagu pertama disesuaikan dengan contoh soal (total durasi = 20.35 menit)
        Lagu[] dataLagu = new Lagu[10];
        dataLagu[0] = new Lagu("Perfect", "Ed Sheeran", 4.23);
        dataLagu[1] = new Lagu("Shivers", "Ed Sheeran", 3.50);
        dataLagu[2] = new Lagu("Fix You", "Coldplay", 3.81);
        dataLagu[3] = new Lagu("Yellow", "Coldplay", 4.29);
        dataLagu[4] = new Lagu("Viva La Vida", "Coldplay", 4.52);
        dataLagu[5] = new Lagu("Photograph", "Ed Sheeran", 4.19);
        dataLagu[6] = new Lagu("The Scientist", "Coldplay", 5.09);
        dataLagu[7] = new Lagu("Thinking Out Loud", "Ed Sheeran", 4.41);
        dataLagu[8] = new Lagu("Paradise", "Coldplay", 4.38);
        dataLagu[9] = new Lagu("A Sky Full of Stars", "Coldplay", 4.28);

        PlaylistRekursif playlistRekursif = new PlaylistRekursif(dataLagu);

        // Menjalankan output utama untuk 5 lagu (sesuai contoh output pada soal)
        jalankanAnalisisUtama(playlistRekursif.getPlaylist(), 5);

        // Menjalankan eksperimen untuk n = 3, 5, dan 10 lagu
        jalankanEksperimen(playlistRekursif.getPlaylist());
    }
}