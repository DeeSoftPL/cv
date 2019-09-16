package pl.deesoft.cv.data.feature.cv.mapper

import pl.deesoft.cv.data.feature.cv.datasource.network.LocationResponse
import pl.deesoft.cv.data.mapper.Mapper
import pl.deesoft.cv.domain.feature.cv.Location
import javax.inject.Inject

class LocationResponseToLocationMapper @Inject constructor(

) : Mapper<LocationResponse, Location> {

    override fun transform(input: LocationResponse): Location {
        return Location(
            city = input.city,
            address = input.address,
            region = input.region
        )
    }
}