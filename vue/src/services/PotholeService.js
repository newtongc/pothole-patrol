import http from 'axios';



export default {
  listPotholes() {
    return http.get('/potholes');
  },
  getPothole(id) {
    return http.get(`/potholes/${id}`);
  },
  createPothole(pothole) {
    return http.post(`/potholes`, pothole)
  },
  updatePothole(id, pothole) {
    return http.put(`/potholes/${id}`, pothole)
  },
  deletePothole(id) {
    return http.delete(`/potholes/${id}`)
  }
}