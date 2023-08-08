import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
});

export default {
    list() {
        return http.get('/potholes');
      },
      get(id) {
        return http.get(`/potholes/${id}`);
      },
      createPothole(pothole) {
        return http.post(`/potholes/add`, pothole)
      },
      updatePothole(id, pothole) {
        return http.put(`/potholes/${id}`, pothole)
      },
      deletePothole(id) {
        return http.delete(`/potholes/${id}`)
      }
}