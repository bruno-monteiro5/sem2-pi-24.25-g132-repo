# Configurar o separador CSV
set datafile separator ","

# Título e eixos
set title "US13 and US14"
set xlabel "Size (number of stations)"
set ylabel "Execution time (seconds)"
set logscale y
set grid
set key top left

# Gerar SVG
set terminal svg size 900,600 enhanced background rgb 'white'
set output "graphic_us29.svg"

# Ignorar cabeçalho e plotar colunas 2 e 3 (US13 e US14)
plot "data_us29.csv" using 1:2 with linespoints title "US13", \
     "data_us29.csv" using 1:3 with linespoints title "US14"
