public class Util {

    public final int RAIO_TERRA = 6371; // Raio médio da Terra em quilômetros

    public double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {

        // Converte as coordenadas de graus para radianos
        double radLat1 = Math.toRadians(lat1);
        double radLon1 = Math.toRadians(lon1);
        double radLat2 = Math.toRadians(lat2);
        double radLon2 = Math.toRadians(lon2);

        // Diferença entre as latitudes e longitudes
        double deltaLat = radLat2 - radLat1;
        double deltaLon = radLon2 - radLon1;

        // Fórmula de Haversine
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(radLat1) * Math.cos(radLat2) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distância em quilômetros
        double distancia = RAIO_TERRA * c;

        return distancia;
    }
}