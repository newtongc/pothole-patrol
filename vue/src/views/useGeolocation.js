import { onUnmounted, onMounted, ref } from 'vue';


export function useGeolocation() {
    // gets coordinates
    const coords = ref({ latitude: 0, longitude: 0 })
    // checks to make sure geolocation api is supported before accessing it
    const isSupported = 'navigator' in window && 'geolocation' in navigator


    //keeps coordinates up to date
    let watcher = null
    onMounted(() => {
        if (isSupported)
            watcher = navigator.geolocation.watchPosition(
                position => (coords.value = position.coords)
            )
    })
    //clears watcher
    onUnmounted(() => {
        if (watcher) navigator.geolocation.clearWatch(watcher)
    })


    return { coords, isSupported }
}