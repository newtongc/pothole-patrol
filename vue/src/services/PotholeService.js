import http from 'axios';



export default {
  listPotholes(search) {
    return http.get('/potholes', { params: search });
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
  },

}