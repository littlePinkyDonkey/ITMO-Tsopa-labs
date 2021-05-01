package andrei.teplyh.services.impl;

import andrei.teplyh.dto.PublishedFeedbackDto;
import andrei.teplyh.dto.TemporaryFeedbackDto;
import andrei.teplyh.entities.UploadedFile;
import andrei.teplyh.entities.accounts.Administrator;
import andrei.teplyh.entities.accounts.User;
import andrei.teplyh.entities.enums.FeedbackStatuses;
import andrei.teplyh.entities.enums.Roles;
import andrei.teplyh.entities.feedbacks.PublishedFeedback;
import andrei.teplyh.entities.feedbacks.TemporaryFeedback;
import andrei.teplyh.exceptions.NoPermossionsException;
import andrei.teplyh.exceptions.UserNotFoundException;
import andrei.teplyh.mappers.TemporaryFeedbackMapper;
import andrei.teplyh.mappers.PublishedFeedbackMapper;
import andrei.teplyh.repositories.PublishedFeedbackRepository;
import andrei.teplyh.repositories.TemporaryFeedbackRepository;
import andrei.teplyh.repositories.UploadedFileRepository;
import andrei.teplyh.services.AdministratorService;
import andrei.teplyh.services.FeedbackService;
import andrei.teplyh.services.FileService;
import andrei.teplyh.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final TemporaryFeedbackRepository temporaryFeedbackRepository;
    private final PublishedFeedbackRepository publishedFeedbackRepository;
    private final UploadedFileRepository uploadedFileRepository;

    private final TemporaryFeedbackMapper temporaryFeedbackMapper;
    private final PublishedFeedbackMapper publishedFeedbackMapper;

    private final AdministratorService administratorService;
    private final UserService userService;
    private final FileService fileService;

    @Autowired
    public FeedbackServiceImpl(
            TemporaryFeedbackRepository temporaryFeedbackRepository,
            PublishedFeedbackRepository publishedFeedbackRepository,
            UploadedFileRepository uploadedFileRepository,
            TemporaryFeedbackMapper temporaryFeedbackMapper,
            PublishedFeedbackMapper publishedFeedbackMapper,
            AdministratorService administratorService,
            UserService userService,
            FileService fileService) {
       this.temporaryFeedbackRepository = temporaryFeedbackRepository;
       this.publishedFeedbackRepository = publishedFeedbackRepository;
       this.uploadedFileRepository = uploadedFileRepository;

       this.temporaryFeedbackMapper = temporaryFeedbackMapper;
       this.publishedFeedbackMapper = publishedFeedbackMapper;

       this.administratorService = administratorService;
       this.userService = userService;
       this.fileService = fileService;
    }

    @Override
    public boolean saveNewTemporaryFeedback(TemporaryFeedbackDto dto) throws Exception {
        TemporaryFeedback entity = temporaryFeedbackMapper.dtoToEntity(dto);

        User author = userService.findUserByLogin(dto.getUserLogin());
        if (author == null) {
            throw new UserNotFoundException("No such user exception");
        }
        if (author.getRole() == Roles.BANNED_USER) {
            throw new NoPermossionsException("You don't have permissions to do this action");
        }
        entity.setAuthor(author);

        Administrator inspector = administratorService.selectAdministratorWithMinimumWork();
        entity.setInspector(inspector);

        entity.setFeedbackStatus(FeedbackStatuses.ON_REVISION);

        List<UploadedFile> uploadedFiles = fileService.saveFiles(dto.getFiles());
        for (UploadedFile file : uploadedFiles) {
            file.setTemporaryFeedback(entity);
        }

        entity.setFiles(uploadedFiles);

        temporaryFeedbackRepository.save(entity);
        uploadedFileRepository.saveAll(uploadedFiles);
        return true;
    }

    @Override
    public boolean savePublishedFeedback(TemporaryFeedback entity) {
        PublishedFeedback publishedFeedback = publishedFeedbackMapper.mapTemporaryFeedbackToPublishedFeedback(entity);
        publishedFeedback.setPublishedDate(new Timestamp(System.currentTimeMillis()));

        uploadedFileRepository.deleteAll(entity.getFiles());
        temporaryFeedbackRepository.delete(entity);

        List<UploadedFile> uploadedFiles = publishedFeedback.getFiles();
        for (UploadedFile file : uploadedFiles) {
            file.setTemporaryFeedback(null);
            file.setPublishedFeedback(publishedFeedback);
        }

        publishedFeedbackRepository.save(publishedFeedback);
        uploadedFileRepository.saveAll(publishedFeedback.getFiles());
        return true;
    }

    @Override
    public void updateTemporaryFeedbackStatus(TemporaryFeedback entity) {
        temporaryFeedbackRepository.save(entity);
    }

    @Override
    public TemporaryFeedback getTemporaryFeedbackById(Long id) {
        return temporaryFeedbackRepository.findTemporaryFeedbackByReviewId(id);
    }

    @Override
    public PublishedFeedback getPublishedFeedbackById(Long id) {
        return publishedFeedbackRepository.findPublishedFeedbackByReviewId(id);
    }

    @Override
    public List<TemporaryFeedbackDto> getAllTemporaryFeedbacks(Long administratorId) {
        Administrator inspector = administratorService.findAdministratorById(administratorId);

        List<TemporaryFeedback> temporaryFeedbacks = temporaryFeedbackRepository.findAllByInspector(inspector);

        return temporaryFeedbackMapper.listEntityToListDto(temporaryFeedbacks);
    }

    @Override
    public List<PublishedFeedbackDto> getAllPublishedFeedbacks(Long userId) {
        User author = userService.findUserById(userId);

        List<PublishedFeedback> publishedFeedbacks = publishedFeedbackRepository.findAllByAuthor(author);

        return publishedFeedbackMapper.listEntityToListDto(publishedFeedbacks);
    }

    @Override
    public boolean updateTemporaryFeedback(TemporaryFeedbackDto dto) throws NullPointerException {
        TemporaryFeedback temporaryFeedback =
                temporaryFeedbackRepository.findTemporaryFeedbackByReviewId(dto.getReviewId());

        if (temporaryFeedback == null) {
            throw new NullPointerException("No such feedback");
        }

        temporaryFeedbackMapper.updateEntity(temporaryFeedback, dto);

        temporaryFeedbackRepository.save(temporaryFeedback);

        return false;
    }
}
