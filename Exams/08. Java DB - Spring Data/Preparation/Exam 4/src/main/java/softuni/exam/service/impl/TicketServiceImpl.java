package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.tickets.TicketCollectionImportDTO;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Plane;
import softuni.exam.models.entities.Ticket;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TicketRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.PlaneService;
import softuni.exam.service.TicketService;
import softuni.exam.service.TownService;
import softuni.exam.util.interfaces.ValidationUtil;
import softuni.exam.util.interfaces.XMLTool;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TicketServiceImpl implements TicketService {
    private static final String TICKETS_FILE_PATH = "src/main/resources/files/xml/tickets.xml";

    private final TicketRepository ticketRepository;
    private final PlaneService planeService;
    private final PassengerService passengerService;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final XMLTool xmlTool;

    public TicketServiceImpl(TicketRepository ticketRepository, PlaneService planeService, PassengerService passengerService, TownService townService, ModelMapper modelMapper, ValidationUtil validator, XMLTool xmlTool) {
        this.ticketRepository = ticketRepository;
        this.planeService = planeService;
        this.passengerService = passengerService;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.xmlTool = xmlTool;
    }

    @Override
    public boolean areImported() {
        return this.ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files.readString(Path.of(TICKETS_FILE_PATH));
    }

    @Override
    public String importTickets() throws JAXBException, IOException {
        if (this.areImported()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();

        this.xmlTool
                .fromXML(TICKETS_FILE_PATH, TicketCollectionImportDTO.class)
                .getTickets()
                .forEach(ticketDTO -> {
                    if (this.validator.isValid(ticketDTO)) {
                        LocalDateTime dateTime = LocalDateTime.parse(ticketDTO.getTakeOff(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                        Town fromTown = this.townService.findTownByTownName(ticketDTO.getFromTown().getName());

                        Town toTown = this.townService.findTownByTownName(ticketDTO.getToTown().getName());

                        Passenger passenger = this.passengerService
                                .findPassengerByEmail(ticketDTO.getPassenger().getEmail());

                        Plane plane = this.planeService
                                .findPlaneByRegisterNumber(ticketDTO.getPlane().getRegisterNumber());

                        Ticket ticket = this.modelMapper.map(ticketDTO, Ticket.class);

                        try {
                            ticket.setTakeOff(dateTime);
                            ticket.setFromTown(fromTown);
                            ticket.setToTown(toTown);
                            ticket.setPassenger(passenger);
                            ticket.setPlane(plane);

                            this.ticketRepository.save(ticket);

                            stringBuilder
                                    .append(String.format("Successfully imported Ticket %s - %s",
                                            ticket.getFromTown().getName(), ticket.getToTown().getName()))
                                    .append(System.lineSeparator());
                        } catch (DataIntegrityViolationException e) {
                            appendInvalidTicket(stringBuilder);
                        }
                    } else {
                        appendInvalidTicket(stringBuilder);
                    }
                });

        return stringBuilder.toString().trim();
    }

    private void appendInvalidTicket(StringBuilder stringBuilder) {
        stringBuilder.append("Invalid Ticket").append(System.lineSeparator());
    }
}
